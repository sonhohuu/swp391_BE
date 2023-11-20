package com.FPTU.controller;

import com.FPTU.dto.AuthenticatedUserDto;
import com.FPTU.dto.UserDTO;
import com.FPTU.dto.ChangePasswordRequest;
import com.FPTU.model.User;
import com.FPTU.repository.UserRepository;
import com.FPTU.security.dto.UserResponse;
import com.FPTU.security.mapper.UserMapper;
import com.FPTU.security.service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("http://127.0.0.1:5173/")
public class UserController {
    private final UserRepository repo;
    private final UserService userService;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final Cloudinary cloudinary;  // Inject the Cloudinary bean

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public List<AuthenticatedUserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/instructor")
    public List<AuthenticatedUserDto> findAllInstructor() {
        return userService.findByRoleInstructor();
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<UserResponse> findUserByUsername(@PathVariable String username) {
        final User user = userService.findByUsername(username);
        final AuthenticatedUserDto userDto = userMapper.convertToAuthenticatedUserDto(user);
        return ResponseEntity.ok(new UserResponse(userDto));
    }

    @PostMapping("/upload/image/{username}")
    public ResponseEntity<?> uploadImage(@PathVariable("username") String username,
                                         @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        // Save the file to Cloudinary and get the upload result
        Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.asMap("public_id", fileName));

        // Check if the upload was successful and retrieve the public URL
        if (uploadResult != null && uploadResult.containsKey("secure_url")) {
            String imageUrl = uploadResult.get("secure_url").toString();

            User user = userService.findByUsername(username);
            // Update the user's image URL
            user.setImg(imageUrl);

            // Save the updated user information
            UserDTO userDTO = userService.updateUser(user);


            return ResponseEntity.ok(userDTO);
        } else {
            // Handle the case where the file upload to Cloudinary failed
            return new ResponseEntity<>("Failed to upload the image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Load the user from the database
        User user = userService.findByUsername(username);

        // Verify the old password
        if (passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            // Update the password with the new one
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            // Save the updated user
            userService.updateUser(user);

            return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Incorrect old password", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateUserByUserName(@RequestBody UserDTO userDTO) {
        final User user = userService.findByUsername(userDTO.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.OK).body("User doesn't exist");
        }
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());

        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("Update user successfully");
    }
}

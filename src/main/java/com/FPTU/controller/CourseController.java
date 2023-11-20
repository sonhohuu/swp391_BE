package com.FPTU.controller;

import com.FPTU.dto.CourseDTO;
import com.FPTU.exceptions.CourseNotFoundException;
import com.FPTU.model.User;
import com.FPTU.service.CourseService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
@CrossOrigin("http://127.0.0.1:5173/")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable("id") Long id) {
        if(!courseService.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        return courseService.findById(id);
    }

    @PostMapping("/upload/image/{course_id}")
    public ResponseEntity<?> uploadImage(@PathVariable("course_id") Long courseId,
                                         @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        // Save the file to Cloudinary and get the upload result
        Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.asMap("public_id", fileName));

        // Check if the upload was successful and retrieve the public URL
        if (uploadResult != null && uploadResult.containsKey("secure_url")) {
            String imageUrl = uploadResult.get("secure_url").toString();

            CourseDTO courseDTO = courseService.findById(courseId);

            courseDTO.setImg(imageUrl);

            courseService.save(courseDTO);


            return ResponseEntity.status(HttpStatus.CREATED).body("Upload successful");
        } else {
            // Handle the case where the file upload to Cloudinary failed
            return new ResponseEntity<>("Failed to upload the image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/customer/{username}")
    public List<CourseDTO> findAllByUserId_RoleCustomer(@PathVariable("username") String username) {
        return courseService.findAllByUserId_RoleCustomer(username);
    }

    @GetMapping("/instructor/{username}")
    public List<CourseDTO> findAllByUserId_RoleInstructor(@PathVariable("username") String username) {
        return courseService.findAllByUserId_RoleInstructor(username);
    }

    @GetMapping("/category/{id}")
    public List<CourseDTO> getCourseByCategoryId(@PathVariable("id") Long id) {
        return courseService.findAllByCategoryId(id);
    }

    @GetMapping("/search")
    public List<CourseDTO> searchCourses(@RequestParam(value = "title", required = false) String title) {
        return courseService.findByName(title);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping()
    public ResponseEntity<CourseDTO> addCourse(@RequestBody @Valid CourseDTO courseDTO) {
        CourseDTO c = courseService.save(courseDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(c);
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PutMapping()
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody @Valid CourseDTO courseDTO) {
        if(!courseService.existsById(courseDTO.getId())) {
            throw new CourseNotFoundException(courseDTO.getId());
        }
        CourseDTO c = courseService.save(courseDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(c);
    }
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long id) {
        if(!courseService.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseService.deleteById(id);
        return ResponseEntity.ok("Delete the course with id" + id);
    }



}
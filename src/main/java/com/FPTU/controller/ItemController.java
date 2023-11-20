package com.FPTU.controller;

import com.FPTU.dto.CourseDTO;
import com.FPTU.dto.ItemDTO;
import com.FPTU.exceptions.CourseNotFoundException;
import com.FPTU.exceptions.ItemNotFoundException;
import com.FPTU.service.ItemService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
@RequestMapping("/items")
@CrossOrigin("http://127.0.0.1:5173/")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();

    }

    @GetMapping("/{id}")
    public ItemDTO getItemById(@PathVariable("id") Long id) {
        return itemService.getItemById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")// Restrict access to ADMIN role
    @PostMapping
    public ResponseEntity<ItemDTO> addItem(@RequestBody @Valid ItemDTO itemDTO) {
        ItemDTO i = itemService.save(itemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(i);
    }

    @PostMapping("/upload/image/{item_id}")
    public ResponseEntity<?> uploadImage(@PathVariable("item_id") Long itemId,
                                         @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        // Save the file to Cloudinary and get the upload result
        Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.asMap("public_id", fileName));

        // Check if the upload was successful and retrieve the public URL
        if (uploadResult != null && uploadResult.containsKey("secure_url")) {
            String imageUrl = uploadResult.get("secure_url").toString();

            ItemDTO itemDTO = itemService.getItemById(itemId);

            itemDTO.setImg(imageUrl);

            itemService.save(itemDTO);


            return ResponseEntity.status(HttpStatus.CREATED).body("Upload successful");
        } else {
            // Handle the case where the file upload to Cloudinary failed
            return new ResponseEntity<>("Failed to upload the image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<ItemDTO> updateItem(@RequestBody @Valid ItemDTO itemDTO) {
        if(!itemService.existsById(itemDTO.getId())) {
            throw new ItemNotFoundException(itemDTO.getId());
        }
        ItemDTO i = itemService.save(itemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(i);
    }

    @PreAuthorize("hasRole('ADMIN')") // Restrict access to ADMIN role
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Long id) {
        if(!itemService.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
        itemService.deleteItemById(id);
        return ResponseEntity.ok("Delete the item with id" + id);

    }

    @GetMapping("/search")
    public List<ItemDTO> searchItems(
            @RequestParam(value = "name", required = false) String name) {
        return itemService.searchItems(name);
    }

}

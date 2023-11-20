package com.FPTU.controller;


import com.FPTU.dto.CourseDiscountDTO;
import com.FPTU.service.CourseDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
@CrossOrigin("http://127.0.0.1:5173/")
public class CourseDiscountController {
    @Autowired
    private CourseDiscountService courseDiscountService;

    @GetMapping
    public List<CourseDiscountDTO> getAllCourseDiscount() {
        return courseDiscountService.getAllCourseDiscount();
    }

    @PostMapping()
    public ResponseEntity<CourseDiscountDTO> addCourseDiscount(@RequestBody CourseDiscountDTO courseDiscountDTO) {
        courseDiscountDTO = courseDiscountService.save(courseDiscountDTO);
        return  ResponseEntity.ok(courseDiscountDTO);
    }

}
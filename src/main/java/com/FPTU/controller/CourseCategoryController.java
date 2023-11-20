package com.FPTU.controller;


import com.FPTU.dto.CourseCategoryDTO;
import com.FPTU.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coursecategory")
@CrossOrigin("http://127.0.0.1:5173/")
public class CourseCategoryController {
  @Autowired
  private CourseCategoryService courseCategoryService;

  @GetMapping
  public List<CourseCategoryDTO> getAllCourseCategory() {
    return courseCategoryService.getAllCourseCategory();
  }

}
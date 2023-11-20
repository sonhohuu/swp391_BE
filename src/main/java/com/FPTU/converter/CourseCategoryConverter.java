package com.FPTU.converter;

import com.FPTU.dto.CourseCategoryDTO;
import com.FPTU.model.CourseCategory;
import org.springframework.stereotype.Component;

@Component
public class CourseCategoryConverter {
  public CourseCategoryDTO toDTO(CourseCategory courseCategory) {
    CourseCategoryDTO courseCategoryDTO = new CourseCategoryDTO();
    courseCategoryDTO.setId(courseCategory.getCategoryId());
    courseCategoryDTO.setName(courseCategory.getName());
    return courseCategoryDTO;
  }
}
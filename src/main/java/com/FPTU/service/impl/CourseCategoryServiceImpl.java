package com.FPTU.service.impl;

import com.FPTU.converter.CourseCategoryConverter;
import com.FPTU.dto.CourseCategoryDTO;
import com.FPTU.model.CourseCategory;
import com.FPTU.repository.CourseCategoryRepository;
import com.FPTU.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

  @Autowired
  private CourseCategoryRepository courseCategoryRepository;
  @Autowired
  private CourseCategoryConverter courseCategoryConverter;
  @Override
  public List<CourseCategoryDTO> getAllCourseCategory() {
    List<CourseCategoryDTO> list = new ArrayList<>();
    for (CourseCategory c : courseCategoryRepository.findAll()) {
      CourseCategoryDTO cDTO = courseCategoryConverter.toDTO(c);
      list.add(cDTO);
    }
    return list;
  }
}
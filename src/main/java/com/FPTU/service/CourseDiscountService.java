package com.FPTU.service;

import com.FPTU.dto.CourseDiscountDTO;

import java.util.List;

public interface CourseDiscountService {
    List<CourseDiscountDTO> getAllCourseDiscount();
    CourseDiscountDTO save(CourseDiscountDTO courseDiscountDTO);
}

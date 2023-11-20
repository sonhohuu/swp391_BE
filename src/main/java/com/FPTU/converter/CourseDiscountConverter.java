package com.FPTU.converter;

import com.FPTU.dto.CourseDiscountDTO;
import com.FPTU.model.CourseDiscount;
import org.springframework.stereotype.Component;

@Component
public class CourseDiscountConverter {
    public CourseDiscount toEntity(CourseDiscountDTO courseDiscountDTO) {
        CourseDiscount courseDiscount = new CourseDiscount();
        courseDiscount.setName(courseDiscountDTO.getName());
        courseDiscount.setPercent(courseDiscountDTO.getPercent());
        courseDiscount.setStartDate(courseDiscountDTO.getStartDate());
        courseDiscount.setEndDate(courseDiscountDTO.getEndDate());
        return courseDiscount;
    }
    public CourseDiscountDTO toDTO(CourseDiscount courseDiscount) {
        CourseDiscountDTO courseDiscountDTO = new CourseDiscountDTO();
        courseDiscountDTO.setId(courseDiscount.getDiscountId());
        courseDiscountDTO.setName(courseDiscount.getName());
        courseDiscountDTO.setCourseId(courseDiscount.getCourse().getCourseId());
        courseDiscountDTO.setPercent(courseDiscount.getPercent());
        courseDiscountDTO.setStartDate(courseDiscount.getStartDate());
        courseDiscountDTO.setEndDate(courseDiscount.getEndDate());
        return courseDiscountDTO;
    }

}
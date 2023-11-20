package com.FPTU.service.impl;

import com.FPTU.converter.CourseDiscountConverter;
import com.FPTU.dto.CourseDiscountDTO;
import com.FPTU.model.Course;
import com.FPTU.model.CourseDiscount;
import com.FPTU.repository.CourseDiscountRepository;
import com.FPTU.repository.CourseRepository;
import com.FPTU.service.CourseDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CourseDiscountServiceImpl implements CourseDiscountService {
    @Autowired
    private CourseDiscountRepository courseDiscountRepository;
    @Autowired
    private CourseDiscountConverter courseDiscountConverter;
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public List<CourseDiscountDTO> getAllCourseDiscount() {
        List<CourseDiscountDTO> listDTO = new ArrayList<>();
        List<CourseDiscount> list = courseDiscountRepository.findAll();
        for (CourseDiscount c : list) {
            CourseDiscountDTO cDTO = courseDiscountConverter.toDTO(c);
            listDTO.add(cDTO);
        }
        return listDTO;
    }

    @Override
    public CourseDiscountDTO save(CourseDiscountDTO courseDiscountDTO) {
        CourseDiscount courseDiscount = new CourseDiscount();
        courseDiscount = courseDiscountConverter.toEntity(courseDiscountDTO);
        Course course = courseRepository.getOne(courseDiscountDTO.getCourseId());
        courseDiscount.setCourse(course);
        courseDiscount = courseDiscountRepository.save(courseDiscount);
        return courseDiscountConverter.toDTO(courseDiscount);
    }


}
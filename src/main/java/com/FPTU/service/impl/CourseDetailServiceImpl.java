package com.FPTU.service.impl;

import com.FPTU.converter.CourseDetailConverter;
import com.FPTU.dto.CourseDetailDTO;
import com.FPTU.model.Course;
import com.FPTU.model.CourseDetail;
import com.FPTU.repository.CourseDetailRepository;
import com.FPTU.repository.CourseRepository;
import com.FPTU.service.CourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseDetailServiceImpl implements CourseDetailService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseDetailRepository courseDetailRepository;
    @Autowired
    private CourseDetailConverter courseDetailConverter;
    @Override
    public CourseDetailDTO save(CourseDetailDTO courseDetailDTO) {
        CourseDetail courseDetail = new CourseDetail();
        if (courseDetailDTO.getId() != null) {
            CourseDetail oldCourseDetail = courseDetailRepository.getOne(courseDetailDTO.getCourseId());
            courseDetail = courseDetailConverter.toEntity(courseDetailDTO, oldCourseDetail);
        } else {
            courseDetail = courseDetailConverter.toEntity(courseDetailDTO);
        }
        Course course = courseRepository.getOne(courseDetailDTO.getCourseId());
        courseDetail.setCourse(course);
        courseDetail = courseDetailRepository.save(courseDetail);
        return courseDetailConverter.toDTO(courseDetail);
    }
    @Override
    public List<CourseDetailDTO> findAllByCourseId(Long courseId) {
        List<CourseDetailDTO> listDTO = new ArrayList<>();
        List<CourseDetail> list = courseDetailRepository.findByCourse_CourseId(courseId);
        for (CourseDetail c: list) {
            CourseDetailDTO courseDetailDTO = courseDetailConverter.toDTO(c);
            listDTO.add(courseDetailDTO);
        }
        return listDTO;
    }

    @Override
    public boolean existsById(Long id) {
        return courseDetailRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id){
        courseDetailRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<CourseDetailDTO> saveAll(List<CourseDetailDTO> list, Long courseId) {
        // Delete existing CourseDetails by courseId
        courseDetailRepository.deleteByCourse_CourseId(courseId);

        // Save new CourseDetails
        List<CourseDetail> savedCourseDetails = list.stream()
                .map(courseDetailDTO -> {
                    CourseDetail courseDetail = courseDetailConverter.toEntity(courseDetailDTO);
                    Course course = new Course();
                    course.setCourseId(courseId);
                    courseDetail.setCourse(course);
                    return courseDetail;
                })
                .collect(Collectors.toList());

        savedCourseDetails = courseDetailRepository.saveAll(savedCourseDetails);
        return savedCourseDetails.stream()
                .map(courseDetail -> courseDetailConverter.toDTO(courseDetail))
                .collect(Collectors.toList());
    }
}
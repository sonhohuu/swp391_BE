package com.FPTU.service.impl;

import com.FPTU.converter.CourseConverter;
import com.FPTU.converter.RatingConverter;
import com.FPTU.dto.CourseDTO;
import com.FPTU.dto.RatingDTO;
import com.FPTU.model.Course;
import com.FPTU.model.CourseCategory;
import com.FPTU.model.Rating;
import com.FPTU.model.User;
import com.FPTU.repository.*;
import com.FPTU.service.CommentService;
import com.FPTU.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseConverter courseConverter;
    @Autowired
    private CourseCategoryRepository courseCategoryRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private CourseDetailRepository courseDetailRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingConverter ratingConverter;

    private List<CourseDTO> getListDTO(List<Course> courses) {
        return courses.stream()
                .map(courseConverter::toDTO)
                .peek(cDTO -> {
                    if (ratingRepository.findAverageRating(cDTO.getId()) != null) {
                        Double rating = ratingRepository.findAverageRating(cDTO.getId());
                        String formattedRating = String.format("%.2f", rating);
                        rating = Double.parseDouble(formattedRating);
                        cDTO.setRating(rating);
                    } else {
                        cDTO.setRating(0.0);
                    }

                    cDTO.setDuration(courseDetailRepository.getSumEstimatedTimeByCourseId(cDTO.getId()));
                    if (cDTO.getDuration() == null) {
                        cDTO.setDuration(0L);
                    }
                    cDTO.setComments(commentService.getCommentsByCourseId(cDTO.getId()));

                })
                .collect(Collectors.toList());
    }
    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return getListDTO(courses);
    }
    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        Course course = new Course();
        if (courseDTO.getId() != null) {
            Course oldCourse = courseRepository.getOne(courseDTO.getId());
            course = courseConverter.toEntity(courseDTO, oldCourse);
        } else {
            course = courseConverter.toEntity(courseDTO);
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDateTime = now.format(formatter);
            course.setCreatedDate(formattedDateTime);
        }
        CourseCategory courseCategory = courseCategoryRepository.getOne(courseDTO.getCategory().getId());
        User user = userRepository.findByUsername(courseDTO.getUser().getUsername());
        course.setCourseCategory(courseCategory);
        course.setUser(user);

        course = courseRepository.save(course);
        return courseConverter.toDTO(course);
    }

    @Override
    public List<CourseDTO> findAllByCategoryId(Long categoryId) {
        List<Course> courses = courseRepository.findByCourseCategory_CategoryId(categoryId);
        return getListDTO(courses);
    }

    @Override
    public boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDTO findById(Long id) {
        Course c = courseRepository.getOne(id);
        CourseDTO cDTO = courseConverter.toDTO(c);
        cDTO.setRating(ratingRepository.findAverageRating(cDTO.getId()));
        if (cDTO.getRating() == null) {
            cDTO.setRating(0.0);
        }
        cDTO.setDuration(courseDetailRepository.getSumEstimatedTimeByCourseId(cDTO.getId()));
        if (cDTO.getDuration() == null) {
            cDTO.setDuration(0L);
        }
        cDTO.setComments(commentService.getCommentsByCourseId(cDTO.getId()));
        return cDTO;
    }

    @Override
    public List<CourseDTO> findByName(String title) {
        List<Course> courses = courseRepository.findByNameContainingIgnoreCase(title);
        return getListDTO(courses);
    }

    @Override
    public List<CourseDTO> findAllByUserId_RoleCustomer(String username) {
        List<Course> courses = courseRepository.findAllByUserIdRoleCustomer(username);
        return getListDTO(courses);
    }

    @Override
    public List<CourseDTO> findAllByUserId_RoleInstructor(String username) {
        User user = userRepository.findByUsername(username);
        List<Course> courses = courseRepository.findByUser_UserId(user.getUserId());
        return getListDTO(courses);
    }


}
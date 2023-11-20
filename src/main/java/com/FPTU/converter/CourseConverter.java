package com.FPTU.converter;

import com.FPTU.dto.CourseDTO;
import com.FPTU.dto.CourseDetailDTO;
import com.FPTU.model.Course;
import com.FPTU.model.CourseDetail;
import com.FPTU.model.CourseLevel;
import com.FPTU.repository.CourseCategoryRepository;
import com.FPTU.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {
    @Autowired
    private CourseCategoryConverter courseCategoryConverter;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    private final String defaultImg = "https://res.cloudinary.com/diaixspmb/image/upload/v1698942338/download.png.png";

    public Course toEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        String img = (courseDTO.getImg() != null) ? courseDTO.getImg() : defaultImg;
        course.setImg(img);
        if (courseDTO.getLevel().equalsIgnoreCase("BEGINNER")) {
            course.setLevel(CourseLevel.BEGINNER);
        }
        if (courseDTO.getLevel().equalsIgnoreCase("INTERMEDIATE")) {
            course.setLevel(CourseLevel.INTERMEDIATE);
        }
        if (courseDTO.getLevel().equalsIgnoreCase("ADVANCED")) {
            course.setLevel(CourseLevel.ADVANCED);
        }
        return course;
    }
    public CourseDTO toDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getCourseId());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setPrice(course.getPrice());
        courseDTO.setCreatedDate(course.getCreatedDate());
        courseDTO.setImg(course.getImg());
        courseDTO.setUser(userMapper.convertToUserDto(course.getUser()));
        courseDTO.setCategory(courseCategoryConverter.toDTO(course.getCourseCategory()));
        if (course.getLevel().equals(CourseLevel.BEGINNER)) {
            courseDTO.setLevel("BEGINNER");
        }
        if (course.getLevel().equals(CourseLevel.INTERMEDIATE)) {
            courseDTO.setLevel("INTERMEDIATE");
        }
        if (course.getLevel().equals(CourseLevel.ADVANCED)) {
            courseDTO.setLevel("ADVANCED");
        }
        return courseDTO;
    }
    public Course toEntity(CourseDTO courseDTO, Course course) {
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        if (courseDTO.getImg() != null) {
            course.setImg(courseDTO.getImg());
        }
        if (courseDTO.getLevel().equalsIgnoreCase("BEGINNER")) {
            course.setLevel(CourseLevel.BEGINNER);
        }
        if (courseDTO.getLevel().equalsIgnoreCase("INTERMEDIATE")) {
            course.setLevel(CourseLevel.INTERMEDIATE);
        }
        if (courseDTO.getLevel().equalsIgnoreCase("ADVANCED")) {
            course.setLevel(CourseLevel.ADVANCED);
        }
        return course;
    }
}

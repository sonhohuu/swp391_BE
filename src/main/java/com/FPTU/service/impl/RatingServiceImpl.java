package com.FPTU.service.impl;

import com.FPTU.converter.RatingConverter;
import com.FPTU.dto.RatingDTO;
import com.FPTU.model.Course;
import com.FPTU.model.Rating;
import com.FPTU.model.User;
import com.FPTU.repository.CourseRepository;
import com.FPTU.repository.RatingRepository;
import com.FPTU.repository.UserRepository;
import com.FPTU.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private RatingConverter ratingConverter;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public RatingDTO save(RatingDTO ratingDTO) {
        Rating rating = new Rating();
        if (ratingDTO.getId() != null) {
            Rating oldRating = ratingRepository.getOne(ratingDTO.getId());
            rating = ratingConverter.toEntity(ratingDTO, oldRating);
        } else {
            rating = ratingConverter.toEntity(ratingDTO);
        }
        Course course = courseRepository.getOne(ratingDTO.getCourseId());
        User user = userRepository.findByUsername(ratingDTO.getUser().getUsername());
        rating.setCourse(course);
        rating.setUser(user);
        rating = ratingRepository.save(rating);
        return ratingConverter.toDTO(rating);
    }

    @Override
    public boolean existByUserNameAndCourseId(String username, Long courseId) {
        if (ratingRepository.findRatingByUserIdAndCourseId(
                userRepository.findByUsername(username).getUserId(), courseId) == null) {
            return false;
        }
        return true;
    }


}

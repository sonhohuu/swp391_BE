package com.FPTU.service;

import com.FPTU.dto.RatingDTO;


public interface RatingService {
    RatingDTO save(RatingDTO ratingDTO);

    boolean existByUserNameAndCourseId(String username, Long courseId);
}
package com.FPTU.converter;
import com.FPTU.dto.RatingDTO;
import com.FPTU.model.Rating;
import com.FPTU.security.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class RatingConverter {

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public Rating toEntity(RatingDTO ratingDTO) {
        Rating rating = new Rating();
        rating.setRating(ratingDTO.getRating());
        return rating;
    }
    public RatingDTO toDTO(Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(rating.getRatingId());
        ratingDTO.setUser(userMapper.convertToUserDto(rating.getUser()));
        ratingDTO.setCourseId(rating.getCourse().getCourseId());
        ratingDTO.setRating(rating.getRating());
        return ratingDTO;
    }

    public Rating toEntity(RatingDTO ratingDTO, Rating rating) {
        rating.setRating(ratingDTO.getRating());
        return rating;
    }

}
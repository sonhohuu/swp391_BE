package com.FPTU.service.impl;

import com.FPTU.converter.CommentConverter;
import com.FPTU.dto.CommentDTO;
import com.FPTU.dto.RatingDTO;
import com.FPTU.model.Comment;
import com.FPTU.model.Course;
import com.FPTU.model.Rating;
import com.FPTU.model.User;
import com.FPTU.repository.CommentRepository;
import com.FPTU.repository.CourseRepository;
import com.FPTU.repository.RatingRepository;
import com.FPTU.repository.UserRepository;
import com.FPTU.security.mapper.UserMapper;
import com.FPTU.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingRepository ratingRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        Comment comment = new Comment();
        if (commentDTO.getId() != null) {
            Comment oldComment = commentRepository.getOne(commentDTO.getId());
            comment = commentConverter.toEntity(commentDTO, oldComment);
        } else {
            comment = commentConverter.toEntity(commentDTO);
        }

        Course course = courseRepository.getOne(commentDTO.getCourseId());
        User user = userRepository.findByUsername(commentDTO.getUser().getUsername());
        comment.setCourse(course);
        comment.setUser(user);
        comment = commentRepository.save(comment);

        Rating rating = new Rating();
        rating.setRating(commentDTO.getRating());
        rating.setUser(user);
        rating.setCourse(course);
        ratingRepository.save(rating);

        return commentConverter.toDTO(comment);
    }

    public List<CommentDTO> getCommentsByCourseId(Long id) {
        List<Comment> comments = commentRepository.findByCourse_CourseId(id);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for ( Comment c: comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(c.getCommentId());
            commentDTO.setComment(c.getComment());
            commentDTO.setUser(userMapper.convertToUserDto(c.getUser()));
            Rating rating = ratingRepository.findRatingByUserIdAndCourseId(c.getUser().getUserId(), c.getCourse().getCourseId());
            if (rating != null) {
                commentDTO.setRating(rating.getRating());
            } else {
                commentDTO.setRating(0L);
            }
            commentDTOS.add(commentDTO);
        }
        return  commentDTOS;
    }

    @Override
    public boolean existCommentByUserNameAndCourseId(String username, Long courseId) {
        if (commentRepository.existByUserIdAndCourseId(userRepository.findByUsername(username).getUserId(), courseId) == null) {
            return false;
        }
        return true;
    }


}
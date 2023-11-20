package com.FPTU.service;

import com.FPTU.dto.CommentDTO;

import java.util.List;


public interface CommentService {
    CommentDTO save(CommentDTO commentDTO);

    List<CommentDTO> getCommentsByCourseId(Long id);

    boolean existCommentByUserNameAndCourseId(String username, Long courseId);
}

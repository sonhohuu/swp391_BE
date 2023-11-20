package com.FPTU.converter;

import com.FPTU.dto.CommentDTO;
import com.FPTU.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    public Comment toEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        return comment;
    }
    public CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getCommentId());
        commentDTO.setCourseId(comment.getCourse().getCourseId());
        commentDTO.setComment(comment.getComment());
        return commentDTO;
    }

    public Comment toEntity(CommentDTO commentDTO, Comment comment) {
        comment.setComment(commentDTO.getComment());
        return comment;
    }

}
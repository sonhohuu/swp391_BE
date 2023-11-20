package com.FPTU.controller;


import com.FPTU.dto.CommentDTO;
import com.FPTU.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
@CrossOrigin("http://127.0.0.1:5173/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping()
    public ResponseEntity<String> addComment(@RequestBody @Valid CommentDTO commentDTO) {
        if (commentService.existCommentByUserNameAndCourseId(commentDTO.getUser().getUsername(), commentDTO.getCourseId())) {
            return ResponseEntity.status(HttpStatus.OK).body("Comment and Rating only 1 time");
        }

        commentService.save(commentDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Comment and Rating added successfully");
    }

}
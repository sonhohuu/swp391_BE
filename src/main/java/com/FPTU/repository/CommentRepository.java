package com.FPTU.repository;

import com.FPTU.dto.CommentDTO;
import com.FPTU.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCourse_CourseId(Long courseId);

    @Query(value = "SELECT * from comment where user_id = :user_id and course_id = :course_id", nativeQuery = true)
    Comment existByUserIdAndCourseId(@Param("user_id")Long userId,@Param("course_id") Long courseId);
}
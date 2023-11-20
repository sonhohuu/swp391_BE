package com.FPTU.repository;

import com.FPTU.model.CourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long> {
    List<CourseDetail> findAll();

    void deleteByCourse_CourseId(Long courseId);

    List<CourseDetail> findByCourse_CourseId(Long courseId);

    boolean existsById(Long id);

    void deleteById(Long id);

    @Query(value = "Select sum(estimated_time) from course_detail where course_id = :courseId", nativeQuery = true)
    Long getSumEstimatedTimeByCourseId(@Param("courseId") Long id);
}
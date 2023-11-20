package com.FPTU.repository;

import com.FPTU.model.OrderDetailCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailCourseRepository extends JpaRepository<OrderDetailCourse, Long> {
}

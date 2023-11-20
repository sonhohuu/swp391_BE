package com.FPTU.repository;

import com.FPTU.model.CourseDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDiscountRepository extends JpaRepository<CourseDiscount, Long> {
    List<CourseDiscount> findAll();
}
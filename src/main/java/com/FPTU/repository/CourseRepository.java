package com.FPTU.repository;

import com.FPTU.dto.CourseDTO;
import com.FPTU.model.Course;
import com.FPTU.model.CourseDetail;
import com.FPTU.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAll();
    List<Course> findByCourseCategory_CategoryId(Long categoryId);

    @Query(value = "SELECT * FROM course WHERE title LIKE %:title%", nativeQuery = true)
    List<Course> findByNameContainingIgnoreCase(@Param("title")String title);

    @Query(value = "Select c.course_id, c.created_date, c.img, c.user_id, title, description, price, category_id, level from Course c \n" +
            "inner join order_detail_course od\n" +
            "on c.course_id = od.course_id\n" +
            "inner join order_course o\n" +
            "on od.order_id = o.order_id\n" +
            "inner join user u\n" +
            "on u.user_id = o.user_id\n" +
            "where u.username = :username", nativeQuery = true)
    List<Course> findAllByUserIdRoleCustomer(@Param("username") String username);

    List<Course> findByUser_UserId(Long id);

    @Query(value = "Select c.* from course c\n" +
            "join order_detail_course od\n" +
            "on c.course_id = od.course_id\n" +
            "join order_course o\n" +
            "on o.order_id = od.order_id\n" +
            "where o.order_id = :order_id", nativeQuery = true)
    List<Course> findCourseByOrderId(@Param("order_id") Long id);
}

package com.FPTU.repository;

import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.dto.OrderRevenueByMonth;
import com.FPTU.model.Course;
import com.FPTU.model.CourseDetail;
import com.FPTU.model.OrderCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
public interface OrderCourseRepository extends JpaRepository<OrderCourse, Long> {
    @Query(value = "SELECT * FROM order_course o ORDER BY o.order_date desc", nativeQuery = true)
    List<OrderCourse> findAllByOrderDateDesc();
    OrderCourse save(OrderCourse orderCourse);
    @Transactional
    @Modifying
    @Query("UPDATE OrderCourse o SET o.status = :newStatus")
    void updateStatus(@Param("newStatus") String newStatus);

    @Query(value = "SELECT MONTH(o.order_date) AS month, YEAR(o.order_date) AS year, SUM(o.total) AS revenue\n" +
            "from order_course o\n" +
            "GROUP BY YEAR(o.order_date), MONTH(o.order_date)\n" +
            "ORDER BY YEAR(o.order_date) DESC, MONTH(o.order_date) DESC", nativeQuery = true)
    List<Object[]> getMonthlyRevenue();

    @Query(value = "SELECT o.course_id, c.price, COUNT(o.course_id) AS course_count\n" +
            "FROM order_detail_course o\n" +
            "JOIN course c ON c.course_id = o.course_id \n" +
            "WHERE o.course_id IN (SELECT c.course_id FROM course c WHERE c.user_id = :Id)\n" +
            "GROUP BY o.course_id, c.price", nativeQuery = true)
    List<Object[]> getInstructorStatic(@Param("Id") Long userId);

    @Query(value = "SELECT c.title, u.name\n" +
            "FROM order_detail_course o\n" +
            "JOIN course c ON c.course_id = o.course_id\n" +
            "JOIN order_course oc on oc.order_id = o.order_id\n" +
            "JOIN user u on u.user_id = oc.user_id\n" +
            "WHERE o.course_id IN (SELECT c.course_id FROM course c WHERE c.user_id = :Id)" +
            "Order by oc.order_date desc", nativeQuery = true)
    List<Object[]> getInstructorHistory(@Param("Id") Long userId);

    List<OrderCourse> findByUser_UserId(Long userId);


}

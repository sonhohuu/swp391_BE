package com.FPTU.repository;

import com.FPTU.model.OrderCourse;
import com.FPTU.model.OrderItem;
import com.FPTU.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query(value = "SELECT * FROM order_item o ORDER BY o.order_date desc", nativeQuery = true)
    List<OrderItem> findAllByOrderDateDesc();
    OrderItem save(OrderItem orderItem);

    @Modifying
    @Query("UPDATE OrderItem o SET o.status = :status WHERE o.orderId = :id")
    void updateStatus(@Param("status") Status status, @Param("id") Long id);

    @Query(value = "SELECT MONTH(o.order_date) AS month, YEAR(o.order_date) AS year, SUM(o.total) AS revenue\n" +
            "from order_item o\n" +
            "GROUP BY YEAR(o.order_date), MONTH(o.order_date)\n" +
            "ORDER BY YEAR(o.order_date) DESC, MONTH(o.order_date) DESC", nativeQuery = true)
    List<Object[]> getMonthlyRevenue();

    List<OrderItem> findByUser_UserId(Long userId);
}

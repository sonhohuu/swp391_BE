package com.FPTU.service;

import com.FPTU.dto.InstructorHistory;
import com.FPTU.dto.InstructorStatic;
import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.dto.OrderRevenueByMonth;

import java.util.List;

public interface OrderCourseService {
    List<OrderCourseDTO> findAll();
    OrderCourseDTO save(OrderCourseDTO orderCourseDTO);

    OrderCourseDTO findById(Long id);

    List<OrderRevenueByMonth> getMonthlyRevenue();

    List<InstructorStatic> getInstructorStatic(String username);

    List<InstructorHistory> getInstructorHistory(String username);

    String updateStatus(Long orderId, String newStatus);

    List<OrderCourseDTO> findByUserNameRoleCustomer(String username);

    List<OrderCourseDTO> findByUserNameRoleInstructor(String username);
}

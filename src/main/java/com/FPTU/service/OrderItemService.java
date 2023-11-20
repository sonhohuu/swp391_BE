package com.FPTU.service;

import com.FPTU.dto.OrderItemDTO;
import com.FPTU.dto.OrderRevenueByMonth;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDTO> findAll();
    OrderItemDTO save(OrderItemDTO orderItemDTO);

    OrderItemDTO findById(Long id);

    String updateStatus(String status, Long id);

    List<OrderRevenueByMonth> getMonthlyRevenue();

    List<OrderItemDTO> findByUserName(String username);
}
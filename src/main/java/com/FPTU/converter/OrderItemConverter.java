package com.FPTU.converter;

import com.FPTU.dto.OrderItemDTO;
import com.FPTU.model.OrderItem;
import com.FPTU.model.Status;
import com.FPTU.security.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {

    private final UserMapper userMapper = UserMapper.INSTANCE;
    public OrderItem toEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setTotal(orderItemDTO.getTotal());
        orderItem.setStatus(Status.PROCESSING);
        orderItem.setAddress(orderItemDTO.getAddress());
        orderItem.setPaymentMethod("Paypal");
        return orderItem;
    }
    public OrderItemDTO toDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getOrderId());
        orderItemDTO.setUser(userMapper.convertToUserDto(orderItem.getUser()));
        orderItemDTO.setTotal(orderItem.getTotal());
        orderItemDTO.setStatus(orderItem.getStatus().toString());
        orderItemDTO.setAddress(orderItemDTO.getAddress());
        orderItemDTO.setPaymentMethod(orderItem.getPaymentMethod());
        return  orderItemDTO;
    }

}
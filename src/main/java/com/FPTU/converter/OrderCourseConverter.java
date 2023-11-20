package com.FPTU.converter;

import com.FPTU.dto.OrderCourseDTO;
import com.FPTU.model.OrderCourse;
import com.FPTU.security.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderCourseConverter {

    private final UserMapper userMapper = UserMapper.INSTANCE;
    public OrderCourse toEntity(OrderCourseDTO orderCourseDTO) {
        OrderCourse orderCourse = new OrderCourse();
        orderCourse.setTotal(orderCourseDTO.getTotal());
        orderCourse.setStatus("New");
        orderCourse.setPaymentMethod("Paypal");
        return orderCourse;
    }
    public OrderCourseDTO toDTO(OrderCourse orderCourse) {
        OrderCourseDTO orderCourseDTO = new OrderCourseDTO();
        orderCourseDTO.setId(orderCourse.getOrderId());
        orderCourseDTO.setTotal(orderCourse.getTotal());
        orderCourseDTO.setUser(userMapper.convertToUserDto(orderCourse.getUser()));
        orderCourseDTO.setStatus(orderCourse.getStatus());
        orderCourseDTO.setPaymentMethod(orderCourse.getPaymentMethod());
        return  orderCourseDTO;
    }

}

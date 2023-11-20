package com.FPTU.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Long id;

    @NotNull(message = "User null")
    private UserDTO user;

    @NotNull(message = "Total null")
    @Positive(message = "Total must be positive")
    private Long total;

    private String orderDate;
    private List<ItemDTO> items;
    private String status;
    private String address;
    private String paymentMethod;
}
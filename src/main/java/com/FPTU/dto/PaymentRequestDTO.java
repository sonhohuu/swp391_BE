package com.FPTU.dto;

import com.FPTU.model.PaypalPaymentIntent;
import com.FPTU.model.PaypalPaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private double total;
    private String currency;
    private PaypalPaymentMethod method;
    private PaypalPaymentIntent intent;
    private String description;
    private String cancelUrl;
    private String successUrl;
}
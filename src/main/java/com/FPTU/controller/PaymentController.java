package com.FPTU.controller;

import javax.servlet.http.HttpServletRequest;

import com.FPTU.model.PaypalPaymentIntent;
import com.FPTU.model.PaypalPaymentMethod;
import com.FPTU.service.PaypalService;
import com.FPTU.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
@Controller
@RestController
@RequestMapping("/payment")
@CrossOrigin("http://127.0.0.1:5173/")
public class PaymentController {
    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";

    public static final String FRONTEND_URL = "http://127.0.0.1:5173/";

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/pay")
    public String pay(HttpServletRequest request, @RequestParam("price") double price) {
        String cancelUrl = FRONTEND_URL + URL_PAYPAL_CANCEL;
        String successUrl = FRONTEND_URL + URL_PAYPAL_SUCCESS;

        try {
            Payment payment = paypalService.createPayment(
                    price,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl
            );

            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay() {
        return "cancel";
    }


}
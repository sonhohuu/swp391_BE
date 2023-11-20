package com.FPTU.controller;

import com.FPTU.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin("http://127.0.0.1:5173/")
public class EmailController {
    @Autowired
    private JavaMailSender javaMailSender;


    private final String recipientEmail = "sonhhse172307@fpt.edu.vn";

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(recipientEmail);
            message.setSubject(emailRequest.getSubject());
            message.setText(emailRequest.getMessage());
            javaMailSender.send(message);

            return "Email sent successfully to " + recipientEmail;
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., log the error)
            e.printStackTrace();
            return "Failed to send email";
        }
    }
}

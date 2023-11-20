package com.FPTU.controller;

import com.FPTU.exceptions.ApiExceptionResponse;
import com.FPTU.exceptions.RegistrationException;
import com.FPTU.security.dto.RegistrationRequest;
import com.FPTU.security.dto.RegistrationResponse;
import com.FPTU.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;


@Log4j2
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<RegistrationResponse> registrationRequest(
      @Valid @RequestBody RegistrationRequest registrationRequest) {
    final RegistrationResponse registrationResponse = userService.register(registrationRequest);
    log.info("Registration successfully: {} ", registrationRequest.toString());
    return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
  }

  @ExceptionHandler(RegistrationException.class)
  ResponseEntity<ApiExceptionResponse> handleRegistrationException(
      RegistrationException exception) {

    final ApiExceptionResponse response = new ApiExceptionResponse(
        exception.getErrorMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

    return ResponseEntity.status(response.getStatus()).body(response);
  }

}

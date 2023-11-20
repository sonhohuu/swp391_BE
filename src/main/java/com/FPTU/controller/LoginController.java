package com.FPTU.controller;

import com.FPTU.model.User;
import com.FPTU.security.dto.LoginRequest;
import com.FPTU.security.dto.LoginResponse;
import com.FPTU.security.jwt.JwtTokenService;
import com.FPTU.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

  private final JwtTokenService jwtTokenService;

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping
  public ResponseEntity<?> loginRequest(@RequestBody LoginRequest loginRequest) {
    User user = userService.findByUsername(loginRequest.getUsername());
    if (user == null) {
      return ResponseEntity.status(HttpStatus.OK).body("Username does not exist!");
    } else {
      if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())) {
        return ResponseEntity.status(HttpStatus.OK).body("Password is not correct!");
      }
    }
    final LoginResponse loginResponse = jwtTokenService.login(loginRequest);
    return ResponseEntity.ok(loginResponse);
  }

}

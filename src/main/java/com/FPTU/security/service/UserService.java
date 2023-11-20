package com.FPTU.security.service;

import com.FPTU.dto.AuthenticatedUserDto;
import com.FPTU.dto.UserDTO;
import com.FPTU.model.User;
import com.FPTU.security.dto.RegistrationRequest;
import com.FPTU.security.dto.RegistrationResponse;

import java.util.List;


public interface UserService {

  User findByUsername(String username);

  RegistrationResponse register(RegistrationRequest registrationRequest);

  AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

  UserDTO updateUser(User user);

  List<AuthenticatedUserDto> findAll();

  List<AuthenticatedUserDto> findByRoleInstructor();
}

package com.FPTU.security.dto;

import com.FPTU.dto.AuthenticatedUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

  private AuthenticatedUserDto user;

}

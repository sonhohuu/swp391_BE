package com.FPTU.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegistrationRequest extends Request {

  @NotEmpty(message = "{registration_name_not_empty}")
  private String name;

  @Email(message = "{registration_email_is_not_valid}")
  @NotEmpty(message = "{registration_email_not_empty}")
  private String email;

  @NotEmpty(message = "{registration_username_not_empty}")
  private String username;

  @NotEmpty(message = "{registration_address_not_empty}")
  private String address = "Go Vap";

  @NotEmpty(message = "{registration_userRole_not_empty}")
  private String role;

  @NotEmpty(message = "{registration_password_not_empty}")
  private String password;

}

package com.FPTU.exceptions;

import lombok.Getter;

@Getter
public class RegistrationException extends BusinessException {
  public RegistrationException(String errorMessage) {
    super(errorMessage);
  }
}

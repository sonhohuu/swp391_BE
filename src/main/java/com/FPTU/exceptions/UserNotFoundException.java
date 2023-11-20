package com.FPTU.exceptions;

import lombok.Getter;

@Getter
public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

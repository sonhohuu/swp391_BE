package com.FPTU.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Log4j2
@RestControllerAdvice
public class ValidationAdvice {

  /**
   * AOP func to handle invalid param error.
   *
   * @param exception Exception
   * @return ResponseEntity
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public final ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception) {

    final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
    final List<String> errorList = fieldErrors.stream().map(
        DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

    final ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(
        HttpStatus.BAD_REQUEST, LocalDateTime.now(), errorList);

    log.warn("Validation errors : {} , Parameters : {}", errorList,
        exception.getBindingResult().getTarget());

    return ResponseEntity.status(validationErrorResponse.getStatus()).body(validationErrorResponse);
  }

}

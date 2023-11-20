package com.FPTU.service;

import com.FPTU.exceptions.RegistrationException;
import com.FPTU.repository.UserRepository;
import com.FPTU.security.dto.RegistrationRequest;
import com.FPTU.utils.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserValidationService {

  private static final String EMAIL_ALREADY_EXISTS = "email_already_exists";

  private static final String USERNAME_ALREADY_EXISTS = "username_already_exists";

  private final UserRepository userRepository;

  private final ExceptionMessageAccessor exceptionMessageAccessor;

  /**
   * validate user.
   *
   * @param registrationRequest RegistrationRequest
   */
  public void validateUser(RegistrationRequest registrationRequest) {

    final String email = registrationRequest.getEmail();
    final String username = registrationRequest.getUsername();
    final Locale locale = registrationRequest.getLocale();
    checkEmail(email, locale);
    checkUsername(username, locale);
  }

  private void checkUsername(String username, Locale locale) {

    final boolean existsByUsername = userRepository.existsByUsername(username);

    if (existsByUsername) {
      log.warn("{} is already being used!", username);
      final String existsUsername = exceptionMessageAccessor.getMessage(
          locale, USERNAME_ALREADY_EXISTS);
      throw new RegistrationException(existsUsername);
    }

  }

  private void checkEmail(String email, Locale locale) {

    final boolean existsByEmail = userRepository.existsByEmail(email);

    if (existsByEmail) {
      log.warn("{} is already being used!", email);
      final String existsEmail = exceptionMessageAccessor.getMessage(locale, EMAIL_ALREADY_EXISTS);
      throw new RegistrationException(existsEmail);
    }
  }

}

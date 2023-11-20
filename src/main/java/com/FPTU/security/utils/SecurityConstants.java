package com.FPTU.security.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityConstants {

  /**
   * Token expiration time 1 days.
   */
  public static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

  /**
   * Secret key for signature.
   */
  public static final String SECRET_KEY = "mySecretKey";

  /**
   * The company who provided token.
   */
  public static final String ISSUER = "www.fpt.com";

  /**
   * Token Prefix.
   * We will use this prefix when parsing JWT Token
   */
  public static final String TOKEN_PREFIX = "Bearer ";

  /**
   * Authorization Prefix in HttpServletRequest.
   * For Example : Authorization: Bearer YWxhZGxa1qea32GVuc2VzYW1l
   */
  public static final String HEADER_STRING = "Authorization";

  public static final String LOGIN_REQUEST_URI = "/login";

  public static final String REGISTRATION_REQUEST_URI = "/register";

  private SecurityConstants() {

    throw new UnsupportedOperationException();
  }

  /**
   * getAuthenticatedUsername func.
   *
   * @return authenticated username from Security Context
   */
  public static String getAuthenticatedUsername() {

    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    return userDetails.getUsername();
  }

}

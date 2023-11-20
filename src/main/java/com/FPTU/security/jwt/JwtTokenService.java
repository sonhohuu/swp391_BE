package com.FPTU.security.jwt;

import com.FPTU.dto.AuthenticatedUserDto;
import com.FPTU.model.User;
import com.FPTU.security.dto.LoginRequest;
import com.FPTU.security.dto.LoginResponse;
import com.FPTU.security.mapper.UserMapper;
import com.FPTU.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class JwtTokenService {

  private final UserService userService;

  private final JwtTokenManager jwtTokenManager;

  private final AuthenticationManager authenticationManager;

  /**
   * login function.
   *
   * @param loginRequest LoginRequest
   * @return LoginResponse
   */
  public LoginResponse login(LoginRequest loginRequest) {

    final String username = loginRequest.getUsername();
    final String password = loginRequest.getPassword();

    final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
        = new UsernamePasswordAuthenticationToken(username, password);

    authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    final AuthenticatedUserDto authenticatedUserDto = userService.findAuthenticatedUserByUsername(
        username);

    final User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);
    final String token = jwtTokenManager.generateToken(user);

    log.info("{} has successfully logged in!", user.getUsername());

    return new LoginResponse(token);
  }

}

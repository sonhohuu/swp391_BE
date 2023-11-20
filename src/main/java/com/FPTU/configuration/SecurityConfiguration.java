package com.FPTU.configuration;

import com.FPTU.security.jwt.JwtAuthenticationEntryPoint;
import com.FPTU.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  private final JwtAuthenticationEntryPoint unauthorizedHandler;

  @Bean
  public AuthenticationManager authenticationManager(
      final AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.cors().and().csrf().disable()
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers("/register", "/login", "/v3/api-docs/**", "/users/**", "/itemcategory/**", "ratings/**", "/email/send",
            "/swagger-ui/**", "/swagger-ui.html", "/actuator/**","/payment/**","/courses/**","/items/**","/ordercourses/**","/orderitems/**", "/coursedetail/**", "/coursecategory/**" ).permitAll()
        //.antMatchers("/items/addItem", "/courses/addCourse").hasRole(UserRole.INSTRUCTOR.name())
        .antMatchers().authenticated()
        .anyRequest().authenticated().and()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().build();

  }


}

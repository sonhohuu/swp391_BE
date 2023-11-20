package com.FPTU;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * Main Application.
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement

@EnableJpaRepositories// Specify the base package
public class SpringBootBoilerplateApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootBoilerplateApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}

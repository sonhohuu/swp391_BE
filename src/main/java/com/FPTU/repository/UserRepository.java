package com.FPTU.repository;

import com.FPTU.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);

  List<User> findAll();

  boolean existsByEmail(String email);

  boolean existsByUsername(String username);

  @Query(value = "Select * from user where user_role = :role", nativeQuery = true)
  List<User> findByRole(@Param("role") String role);

}

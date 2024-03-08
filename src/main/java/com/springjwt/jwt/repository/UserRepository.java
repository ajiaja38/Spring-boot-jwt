package com.springjwt.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springjwt.jwt.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
  
}

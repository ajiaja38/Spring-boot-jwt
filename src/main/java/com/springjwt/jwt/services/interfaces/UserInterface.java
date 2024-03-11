package com.springjwt.jwt.services.interfaces;

import java.util.List;

import com.springjwt.jwt.dto.req.CreateUserDto;
import com.springjwt.jwt.dto.req.LoginDto;
import com.springjwt.jwt.entity.User;

public interface UserInterface {
  User registerUser(CreateUserDto createUserDto);
  List<User> getAllUser();
  User getUserById(String id);
  User validateCredentials(LoginDto loginDto);
}

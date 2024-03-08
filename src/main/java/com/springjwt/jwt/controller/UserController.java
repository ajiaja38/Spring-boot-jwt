package com.springjwt.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.jwt.dto.req.CreateUserDto;
import com.springjwt.jwt.entity.User;
import com.springjwt.jwt.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<User> savedUserHandler(@RequestBody CreateUserDto createUserDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.registerUser(createUserDto));
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUserHandler() {
    return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUser());
  }

}

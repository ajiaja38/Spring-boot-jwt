package com.springjwt.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.jwt.dto.req.CreateUserDto;
import com.springjwt.jwt.entity.User;
import com.springjwt.jwt.services.UserService;
import com.springjwt.jwt.utils.constant.EndpointConstant;
import com.springjwt.jwt.utils.constant.MessageConstant;
import com.springjwt.jwt.utils.res.ResponseDataArr;
import com.springjwt.jwt.utils.res.ResponseDataObj;

@RestController
@RequestMapping(
  EndpointConstant.API +
  EndpointConstant.VERSION +
  EndpointConstant.USER
)
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<ResponseDataObj<User>> savedUserHandler(@RequestBody CreateUserDto createUserDto) {
    return ResponseEntity
    .status(HttpStatus.CREATED)
    .body(
      new ResponseDataObj<>(
        String.format(MessageConstant.MESSAGE_INSERTED, createUserDto.getUsername()),
        this.userService.registerUser(createUserDto)
      )
    );
  }

  @GetMapping
  public ResponseEntity<ResponseDataArr<User>> getAllUserHandler() {
    return ResponseEntity
    .status(HttpStatus.OK)
    .body(
      new ResponseDataArr<>(
        String.format(MessageConstant.MESSAGE_GETTED, "Users"),
        this.userService.getAllUser()
      )
    );
  }

}

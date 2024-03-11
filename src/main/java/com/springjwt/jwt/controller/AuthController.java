package com.springjwt.jwt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.jwt.dto.req.LoginDto;
import com.springjwt.jwt.entity.User;
import com.springjwt.jwt.services.UserService;
import com.springjwt.jwt.utils.constant.EndpointConstant;
import com.springjwt.jwt.utils.jwt.JwtUtils;
import com.springjwt.jwt.utils.res.ResponseDataObj;

@RestController
@RequestMapping(
  EndpointConstant.API +
  EndpointConstant.VERSION + 
  EndpointConstant.AUTH
)
public class AuthController {

  @Autowired
  private UserService userService;

  @Autowired
  private JwtUtils jwtUtils;
  
  @PostMapping("/login")
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public ResponseEntity<ResponseDataObj<Map<String, Object>>> loginHandler(@RequestBody LoginDto loginDto) {
    try {
      User user = this.userService.validateCredentials(loginDto);
      String accessToken = this.jwtUtils.generateToken(user);

      Map<String, Object> res = new HashMap<>();
      res.put("accessToken", accessToken);
      
      ResponseDataObj response = new ResponseDataObj<>();
      response.setMessage("Berhasil Login");
      response.setData(res);

      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (RuntimeException e) {
      return ResponseEntity
      .status(HttpStatus.UNAUTHORIZED)
      .body(
        new ResponseDataObj<>(
          "Gagal Login " + e.getMessage(),
          null
        )
      );
    }
  }

}

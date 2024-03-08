package com.springjwt.jwt.dto.req;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CreateUserDto {

  private String username;

  private String email;

  private String password;

  private List<String> roles;

}

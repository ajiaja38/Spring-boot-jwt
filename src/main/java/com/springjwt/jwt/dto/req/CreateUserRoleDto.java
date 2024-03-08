package com.springjwt.jwt.dto.req;

import com.springjwt.jwt.utils.constant.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRoleDto {
  private RoleEnum role;
}

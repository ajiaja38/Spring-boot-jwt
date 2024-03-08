package com.springjwt.jwt.dto.req;

import com.springjwt.jwt.utils.constant.RoleEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateRoleDto {
  private RoleEnum role;
}

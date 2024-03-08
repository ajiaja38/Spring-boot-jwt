package com.springjwt.jwt.services.interfaces;

import java.util.List;

import com.springjwt.jwt.dto.req.CreateRoleDto;
import com.springjwt.jwt.entity.Role;

public interface RoleInterface {
  Role saveRole(CreateRoleDto createRoleDto);
  List<Role> getAllRole();
}

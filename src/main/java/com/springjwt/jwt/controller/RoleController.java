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

import com.springjwt.jwt.dto.req.CreateRoleDto;
import com.springjwt.jwt.entity.Role;
import com.springjwt.jwt.services.RoleService;
import com.springjwt.jwt.utils.constant.EndpointConstant;

@RestController
@RequestMapping(
  EndpointConstant.API +
  EndpointConstant.VERSION +
  EndpointConstant.ROLE
)
public class RoleController {
  
  @Autowired
  private RoleService roleService;

  @PostMapping
  public ResponseEntity<Role> saveRoleHandler(@RequestBody CreateRoleDto createRoleDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.saveRole(createRoleDto));
  }

  @GetMapping
  public ResponseEntity<List<Role>> getAllRoleHandler() {
    return ResponseEntity.status(HttpStatus.OK).body(this.roleService.getAllRole());
  }

}

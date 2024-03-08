package com.springjwt.jwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjwt.jwt.dto.req.CreateRoleDto;
import com.springjwt.jwt.entity.Role;
import com.springjwt.jwt.repository.RoleRepository;
import com.springjwt.jwt.services.interfaces.RoleInterface;

@Service
public class RoleService implements RoleInterface {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role saveRole(CreateRoleDto createRoleDto) {
    System.out.println(createRoleDto.getRole());
    
    Role role = new Role();
    role.setRole(createRoleDto.getRole());

    return this.roleRepository.save(role);
  }

  @Override
  public List<Role> getAllRole() {
    return this.roleRepository.findAll();
  }
  
}

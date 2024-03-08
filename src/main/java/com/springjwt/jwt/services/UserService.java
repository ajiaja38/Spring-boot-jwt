package com.springjwt.jwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springjwt.jwt.dto.req.CreateUserDto;
import com.springjwt.jwt.entity.Role;
import com.springjwt.jwt.entity.User;
import com.springjwt.jwt.repository.RoleRepository;
import com.springjwt.jwt.repository.UserRepository;
import com.springjwt.jwt.services.interfaces.UserInterface;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserInterface {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Override
  @Transactional
  public User registerUser(CreateUserDto createUserDto) {
    User user = new User();
    user.setUsername(createUserDto.getUsername());
    user.setEmail(createUserDto.getEmail());
    user.setPassword(this.passwordEncoder.encode(createUserDto.getPassword()));
    
    List<Role> roles = this.roleRepository.findByRoleIn(createUserDto.getRoles());
    
    user.setUserRoles(roles);
    return this.userRepository.save(user);
  }

  @Override
  public List<User> getAllUser() {
    return this.userRepository.findAll();
  }

  @Override
  public User getUserById(String id) {
    return this.userRepository.findById(id).orElseThrow(
      () -> new RuntimeException("User tidak ditemukan")
    );
  }
  
}

package com.springjwt.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springjwt.jwt.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
  List<Role> findByRoleIn(List<String> role);
}

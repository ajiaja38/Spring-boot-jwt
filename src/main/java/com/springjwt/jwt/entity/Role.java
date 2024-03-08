package com.springjwt.jwt.entity;

import java.util.UUID;

import com.springjwt.jwt.utils.constant.RoleEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "mst_role")
public class Role {
  
  @Id
  private String id;
  
  @Enumerated(EnumType.STRING)
  private RoleEnum role = RoleEnum.ROLE_USER;

  @PrePersist
  public void prefixId() {
    this.id = "role-" + UUID.randomUUID();
  }
}

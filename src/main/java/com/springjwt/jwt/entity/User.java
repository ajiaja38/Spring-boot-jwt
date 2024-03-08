package com.springjwt.jwt.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Setter
@Getter
@ToString
@Table(name = "mst_user")
public class User {

  @Id
  private String id;

  private String username;

  private String email;

  @JsonIgnore
  private String password;

  private Date createdAt = new Date();

  private Date updatedAt = new Date();

  @ManyToMany
  @JoinTable(
    name = "trx_user_role",
    joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name="role_id")
  )
  private List<Role> userRoles;

  @PrePersist
  public void prefixId() {
    this.id = "user-" + UUID.randomUUID();
  }
  
}

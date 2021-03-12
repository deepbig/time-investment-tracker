package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
    })
@NoArgsConstructor
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "username", length = 100, unique = true, nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

//  @ElementCollection(fetch = FetchType.EAGER)
//  @Column(name = "roles")
//  private Set<RoleType> roles = new HashSet<>();

//  @Column(name = "token")
//  private String adminToken;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

}
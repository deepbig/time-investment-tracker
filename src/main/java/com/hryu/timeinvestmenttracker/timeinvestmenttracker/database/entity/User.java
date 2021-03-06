package com.hryu.timeinvestmenttracker.timeinvestmenttracker.database.entity;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.type.RoleType;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user",
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

  @OneToMany(mappedBy = "user")
  private Set<Category> categories = new HashSet<>();

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "roles")
  private Set<RoleType> roles = new HashSet<>();

  @Column(name = "token")
  private String adminToken;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

}
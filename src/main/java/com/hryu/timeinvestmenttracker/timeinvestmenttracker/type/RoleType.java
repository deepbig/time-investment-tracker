package com.hryu.timeinvestmenttracker.timeinvestmenttracker.type;

import lombok.Getter;

public enum RoleType {
  ROLE_USER("USER"),
  ROLE_ADMIN("ADMIN");

  @Getter
  private final String literal;

  RoleType(String literal) {
    this.literal = literal;
  }
}

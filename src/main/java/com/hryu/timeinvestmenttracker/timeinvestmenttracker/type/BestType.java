package com.hryu.timeinvestmenttracker.timeinvestmenttracker.type;

import lombok.Getter;

public enum BestType {
  hour(0),
  count(1);

  @Getter
  private final int code;

  BestType(int code) {
    this.code = code;
  }
}

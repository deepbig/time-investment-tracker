package com.hryu.timeinvestmenttracker.timeinvestmenttracker.type;

import lombok.Getter;

public enum PostingType {
  ACTIVITY(0),
  RESULT(1);

  @Getter
  private final int code;

  PostingType(int code) {
    this.code = code;
  }
}


package com.hryu.timeinvestmenttracker.timeinvestmenttracker.type;

import lombok.Getter;

public enum PostingType {
  ACTIVITY_TYPE(0),
  RESULT_TYPE(1);

  @Getter
  private final int code;

  PostingType(int code) {
    this.code = code;
  }
}


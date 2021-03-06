package com.hryu.timeinvestmenttracker.timeinvestmenttracker.type;

import lombok.Getter;

public enum PostingType {
  activity(0),
  result(1);

  @Getter
  private final int code;

  PostingType(int code) {
    this.code = code;
  }
}


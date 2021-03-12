package com.hryu.timeinvestmenttracker.timeinvestmenttracker.error;

import  com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.ErrorCode;
import lombok.Getter;

public class CBadTokenException extends RuntimeException {

  @Getter
  private final ErrorCode code;

  public CBadTokenException(ErrorCode code, String msg, Throwable t) {
    super(msg, t);

    this.code = code;
  }

  public CBadTokenException(ErrorCode code, String msg) {
    super(msg);
    this.code = code;
  }

  public CBadTokenException(ErrorCode code) {
    super();
    this.code = code;
  }
}

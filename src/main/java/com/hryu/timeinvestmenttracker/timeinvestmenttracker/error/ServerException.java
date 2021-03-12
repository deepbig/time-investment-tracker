package com.hryu.timeinvestmenttracker.timeinvestmenttracker.error;

import  com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.ErrorCode;
import lombok.Getter;
import lombok.NonNull;

public class ServerException extends Exception {

  @Getter
  private final ErrorCode code;

  public ServerException(@NonNull ErrorCode code) {
    super(code.getMsg());
    this.code = code;
  }

  public ServerException(@NonNull ErrorCode code, Object... args) {
    super(String.format(code.getMsg(), args));
    this.code = code;
  }

  public ServerException(String message, @NonNull ErrorCode code) {
    super(message);
    this.code = code;
  }

  public ServerException(String message, Throwable cause, ErrorCode code) {
    super(message, cause);
    this.code = code;
  }

  public ServerException(Throwable cause, ErrorCode code) {
    super(cause);
    this.code = code;
  }
}

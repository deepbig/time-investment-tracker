package com.hryu.timeinvestmenttracker.timeinvestmenttracker.error;

public class CEmailSigninFailedException extends RuntimeException {

  public CEmailSigninFailedException(String msg, Throwable t) {
    super(msg, t);
  }

  public CEmailSigninFailedException(String msg) {
    super(msg);
  }

  public CEmailSigninFailedException() {
    super();
  }
}
package com.hryu.timeinvestmenttracker.timeinvestmenttracker.error;

public class CDuplicateLonginException extends RuntimeException {

  public CDuplicateLonginException(String msg, Throwable t) {
    super(msg, t);
  }

  public CDuplicateLonginException(String msg) {
    super(msg);
  }

  public CDuplicateLonginException() {
    super();
  }
}

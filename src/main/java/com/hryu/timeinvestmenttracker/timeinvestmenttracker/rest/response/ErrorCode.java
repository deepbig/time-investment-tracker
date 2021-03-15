package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response;

import lombok.Getter;

public enum ErrorCode {

  TOKEN_REFRESHED(true, 99, "Token already refreshed"),
  SUCCESS(true, 0, "Success"),
  FAIL(false, -1, "Failed"),
  FAIL_COMMON_INTERRUPTED(false, -99, "Interrupted when processing"),
  FAIL_COMMON_IOEXCEPTION(false, -100, "%s"),
  FAIL_COMMON_COMMEXCEPTION(false, -200, ""),
  FAIL_COMMON_SQLEXCEPTION(false, -300, ""),
  FAIL_COMMON_SOCKETEXCEPTION(false, -400, ""),
  FAIL_AUTH_TOKEN_INCORRECT_EXPIRED(false, -500, "Token is incorrect or expired."),
  FAIL_AUTH_LOGIN_REQUIRED(false, -501, "Log in is required."),
  FAIL_AUTH_DUPLICATE_LOGIN(false, -502, "Another session is already signed in"),
  FAIL_AUTH_LOGIN_FAILED(false, -503, "Log in is failed, check id and password"),
  FAIL_AUTH_TOKEN_REFRESH_NOT_AFFECTED(false, -504,
      "Token refresh not affected because another session already do it."),
  FAIL_AUTH_CHANGE_NAME_PWD_EMPTY(false, -505, "Both user name and password are empty."),
  FAIL_AUTH_USER_NOT_EXIST(false, -506, "User not exists"),
  FAIL_AUTH_TOKEN_ALREADY_REFRESHED(false, -507, "Token already changed by another session."),

  //에러 코드 변경 시 프론트에 반영해야 함.
  FAIL_USER_SIGNIN_BY_EMPTY_BODY(false, -11001,
      "Fail to sign you in because you did not type your username/password."),
  FAIL_USER_SIGNUP_BY_NAME_ALREADY_EXIST(false, -11002,
      "Fail to sign you up because specified username already exists"),
  FAIL_USER_SIGNIN_BY_ROLE_NOT_EXISTS(false, -11003,
      "Fail to assign a role because the role does not exist"),
  FAIL_USER_CHECK_BY_INVALID_TOKEN(false, -11004,
      "Fail to check a token because the token is not valid"),
  FAIL_USER_CHECK_BY_TOKEN_NOT_EXIST(false, -11005,
      "Fail to check a token because the token does not exist"),
  FAIL_ADMIN_USER_CHECK_BY_INVALID_TOKEN(false, -11006,
      "Fail to check your token because the token is not valid"),
  FAIL_USER_SIGNIN_BY_INVAILD_FORM(false, -11007,
      "Fail to sign you in because the login form is not valid"),
  FAIL_USER_UPDATE_BY_PASSWORD_NOT_MATCH(false, -11008,
      "Fail to update your password because your current password is not correct."),
  FAIL_USER_UPDATE_BY_USER_NOT_EXISTS(false, -11009,
      "Fail to update your password because your username is not exists."),
  FAIL_USER_SIGNIN_BY_USER_NOT_EXISTS(false, -11003,
      "Fail to get user information because your username is not exists."),
  ;

  @Getter
  private final boolean success;
  @Getter
  private final int code;
  @Getter
  private final String msg;

  ErrorCode(boolean success, int code, String msg) {
    this.success = success;
    this.code = code;
    this.msg = msg;
  }

  public static ErrorCode valueOf(int code) {
    for (ErrorCode rc : values()) {
      if (code == rc.code) {
        return rc;
      }
    }

    return null;
  }
}

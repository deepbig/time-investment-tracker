package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.advice;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.error.CBadTokenException;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.error.CDuplicateLonginException;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.error.CEmailSigninFailedException;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.error.ServerException;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.CommonResult;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.ErrorCode;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service.ResponseService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

  private final ResponseService responseService;

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.OK)
  protected CommonResult defaultException(HttpServletRequest req, Exception e) {
    if (e instanceof ServerException) {
      ServerException se = (ServerException) e;
      return new CommonResult(se.getCode(), e.getMessage());
    } else if (e instanceof IOException) {
      return new CommonResult(ErrorCode.FAIL_COMMON_IOEXCEPTION, e.getMessage());
    } else if (e instanceof SQLException) {
      return new CommonResult(ErrorCode.FAIL_COMMON_SQLEXCEPTION, e.getMessage());
    } else if (e instanceof CBadTokenException) {
      return new CommonResult(((CBadTokenException) e).getCode(), e.getMessage());
    } else if (e instanceof CDuplicateLonginException) {
      return new CommonResult(ErrorCode.FAIL_AUTH_DUPLICATE_LOGIN, e.getMessage());
    } else if (e instanceof CEmailSigninFailedException) {
      return new CommonResult(ErrorCode.FAIL_AUTH_LOGIN_FAILED, e.getMessage());
    } else {
      CommonResult res = responseService.getFailResult();
      res.setMsg(e.getMessage());
      return res;
    }
  }
}

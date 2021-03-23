package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class CommonResult {

  public static final CommonResult SUCCESS_RESPONSE = new CommonResult(ErrorCode.SUCCESS);

  private boolean success;

  private int code;

  private String msg;

//  private HttpStatus status;
//
//  private String error_code;
//
//  private String detail;
//
//  private LocalDateTime timeStamp;

  public CommonResult(ErrorCode code, String msg) {
    this.success = code.isSuccess();
    this.code = code.getCode();
    if (msg == null) {
      this.msg = code.getMsg();
    } else {
      this.msg = msg;
    }
  }

  public CommonResult(ErrorCode code) {
    this.success = code.isSuccess();
    this.code = code.getCode();
    this.msg = code.getMsg();
  }

//  public static final class ApiErrorResponseBuilder {
//
//    private boolean success;
//    private int code;
//    private String msg;
//    private HttpStatus status;
//    private String error_code;
//    private String detail;
//    private LocalDateTime timeStamp;
//
//    public ApiErrorResponseBuilder() {
//    }
//
//    public static ApiErrorResponseBuilder anApiErrorResponse() {
//      return new ApiErrorResponseBuilder();
//    }
//
//    public ApiErrorResponseBuilder withSuccess(boolean success) {
//      this.success = success;
//      return this;
//    }
//
//    public ApiErrorResponseBuilder withCode(int code) {
//      this.code = code;
//      return this;
//    }
//
//    public ApiErrorResponseBuilder withStatus(HttpStatus status) {
//      this.status = status;
//      return this;
//    }
//
//    public ApiErrorResponseBuilder withError_code(String error_code) {
//      this.error_code = error_code;
//      return this;
//    }
//
//    public ApiErrorResponseBuilder withMsg(String msg) {
//      this.msg = msg;
//      return this;
//    }
//
//    public ApiErrorResponseBuilder withDetail(String detail) {
//      this.detail = detail;
//      return this;
//    }
//
//    public ApiErrorResponseBuilder atTime(LocalDateTime timeStamp) {
//      this.timeStamp = timeStamp;
//      return this;
//    }
//
//    public CommonResult build() {
//      CommonResult apiErrorResponse = new CommonResult();
//      apiErrorResponse.success = this.success;
//      apiErrorResponse.code = this.code;
//      apiErrorResponse.status = this.status;
//      apiErrorResponse.error_code = this.error_code;
//      apiErrorResponse.detail = this.detail;
//      apiErrorResponse.msg = this.msg;
//      apiErrorResponse.timeStamp = this.timeStamp;
//      return apiErrorResponse;
//    }
//
//  }
}


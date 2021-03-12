package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.service;

import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.CommonResult;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.ErrorCode;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.ListResult;
import com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response.SingleResult;
import java.util.List;
import org.springframework.stereotype.Service;

@Service // 해당 Class 가 Service 임을 명시합니다.
public class ResponseService {

  // 단일건 결과를 처리하는 메소드
  public <T> SingleResult<T> getSingleResult(T data) {
    SingleResult<T> result = new SingleResult<>();
    result.setData(data);
    setSuccessResult(result);
    return result;
  }

  // 다중건 결과를 처리하는 메소드
  public <T> ListResult<T> getListResult(List<T> list) {
    ListResult<T> result = new ListResult<>();
    result.setList(list);
    setSuccessResult(result);
    return result;
  }

  // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
  private void setSuccessResult(CommonResult result) {
    result.setSuccess(true);
    result.setCode(ErrorCode.SUCCESS.getCode());
    result.setMsg(ErrorCode.SUCCESS.getMsg());
  }

  // 실패 결과만 처리하는 메소드
  public CommonResult getFailResult() {
    CommonResult result = new CommonResult();
    result.setSuccess(false);
    result.setCode(ErrorCode.FAIL.getCode());
    result.setMsg(ErrorCode.FAIL.getMsg());
    return result;
  }

  // enum 으로 api 요청 결과에 대한 code, message 를 정의합니다.
}

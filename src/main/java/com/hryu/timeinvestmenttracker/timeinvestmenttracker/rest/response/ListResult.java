package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListResult<T> extends CommonResult {

  private List<T> list;
}


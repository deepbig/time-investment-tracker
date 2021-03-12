package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResult extends CommonResult {

  @JsonProperty("generated_id")
  private Long newId;
}


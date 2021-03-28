package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResultPostDto extends PostingDto {

  @JsonProperty(value = "test_duration", required = true)
  private int testDuration;

  @JsonProperty(value = "test_count", required = true)
  private int testCount;
}

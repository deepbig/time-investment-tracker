package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewPasswordDto {

  @JsonProperty(value = "update", required = true)
  private String update;
}

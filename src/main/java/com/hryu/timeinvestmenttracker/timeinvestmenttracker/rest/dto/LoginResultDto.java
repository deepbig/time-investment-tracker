package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class LoginResultDto {

  @JsonProperty(value = "username")
  private String username;

  public LoginResultDto(String username) {
    this.username = username;
  }

}
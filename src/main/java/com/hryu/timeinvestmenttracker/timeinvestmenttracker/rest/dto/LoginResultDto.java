package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class LoginResultDto {

  @JsonProperty(value = "username")
  private String username;

  @JsonProperty(value= "roles")
  private List<String> roles;

  public LoginResultDto(String username, List<String> roles) {
    this.username = username;
    this.roles = roles;
  }

}
package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import lombok.Data;

@Data
public class NewUserDto {

  @JsonProperty(value = "username", required = true)
  private String username;

  private Set<String> role;

  @JsonProperty(value = "password", required = true)
  private String password;
}

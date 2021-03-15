package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

public class ActivityPostDto extends PostingDto {

  @JsonProperty(value = "practice_duration", required = true)
  private int practiceDuration;

  @JsonProperty(value = "activity_count", required = true)
  private int activityCount;
}

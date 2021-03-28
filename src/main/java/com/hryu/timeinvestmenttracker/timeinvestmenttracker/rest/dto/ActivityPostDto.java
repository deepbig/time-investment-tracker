package com.hryu.timeinvestmenttracker.timeinvestmenttracker.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

//@EqualsAndHashCode(callSuper = true)로 설정시 부모 클래스 필드 값들도 동일한지 체크하며, false(기본값)일 경우 자신 클래스의 필드 값만 고려한다.
//@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityPostDto extends PostingDto {

  @JsonProperty(value = "practice_duration", required = true)
  private int practiceDuration;

  @JsonProperty(value = "activity_count", required = true)
  private int activityCount;
}

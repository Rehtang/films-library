package ru.rehtang.films.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestDto {
  @JsonProperty("i")
  private String iMdbId ;
  private String plot ;
}

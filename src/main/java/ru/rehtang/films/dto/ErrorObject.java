package ru.rehtang.films.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Specify internal service error")
public class ErrorObject {

  @Schema(required = true)
  private String code;

  @Schema(nullable = true)
  private String message;
}
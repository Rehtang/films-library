package ru.rehtang.films.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class JwtResponse {

  private UUID uuid;
  private String token;
  private String type = "Bearer";
  private String username;
  private List<String> roles;
}
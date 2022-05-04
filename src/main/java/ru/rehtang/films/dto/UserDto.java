package ru.rehtang.films.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private UUID uuid;
  private String username;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
}

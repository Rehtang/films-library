package ru.rehtang.films.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rehtang.films.dto.BasicResponse;
import ru.rehtang.films.dto.UserDto;
import ru.rehtang.films.service.UserService;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public BasicResponse<Void> registerUser(@RequestBody UserDto user) {
    userService.register(user);
    return new BasicResponse<>();
  }

  @GetMapping("/{id}")
  public UserDto getUserById(@PathVariable UUID id) {
    return userService.getUserById(id);
  }

  @PatchMapping
  public BasicResponse<Void> editUser(@RequestBody UserDto user) {
    userService.editUserData(user);
    return new BasicResponse<>();
  }
}
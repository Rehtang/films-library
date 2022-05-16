package ru.rehtang.films.service;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rehtang.films.dto.ApiResponseDto;
import ru.rehtang.films.dto.BasicResponse;
import ru.rehtang.films.mapper.FilmMapper;
import ru.rehtang.films.persistence.entity.User;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

  private final UserService userService;
  private final FilmsProviderService filmService;
  private final FilmMapper filmMapper;

  @GetMapping("/user")
  public BasicResponse<List<User>> allUsers() {
    return new BasicResponse<List<User>>().setData(userService.findAll());
  }

  @GetMapping("/user/{id}")
  public BasicResponse<User> getUserById(@PathVariable UUID id) {
    return new BasicResponse<User>().setData(userService.findById(id));
  }

  @DeleteMapping("/user/{id}")
  public BasicResponse<Void> deleteUser(@PathVariable UUID id) {
    userService.deleteById(id);
    return new BasicResponse<>();
  }

  @DeleteMapping("/film/{imdbId}")
  public BasicResponse<Void> deleteFilm(@PathVariable String imdbId) {
    filmService.deleteById(imdbId);
    return new BasicResponse<>();
  }

  @PostMapping("/film")
  public BasicResponse<Void> addFilm(@RequestBody ApiResponseDto film) {
    filmService.save(filmMapper.toEntity(film));
    return new BasicResponse<>();
  }

  @PutMapping("/film")
  public BasicResponse<Void> editFilm(@RequestBody ApiResponseDto film) {
    filmService.save(filmMapper.toEntity(film));
    return new BasicResponse<>();
  }
}
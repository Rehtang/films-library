package ru.rehtang.films.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rehtang.films.dto.ApiResponseDto;
import ru.rehtang.films.service.FilmsProviderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FilmsRestController {

  private final FilmsProviderService service;

  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ApiResponseDto getFilms(@RequestParam String id) {
    return service.receiveFilms(id, null, null);
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ApiResponseDto> getFilmsByTitle(@RequestParam String title) {
    return service.receiveFilmsByTitle(title, null, null, null);
  }

  @GetMapping(value = "/baseSearch/yearEqual", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ApiResponseDto> getFilmsByYear(@RequestParam String year) {
    return service.findFilmByYear(year);
  }

  @GetMapping(
      value = "/baseSearch/yearGreaterThanEqual",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ApiResponseDto> getFilmsByYearGreaterThanEqual(@RequestParam String year) {
    return service.findFilmByYearGreaterThanEqual(year);
  }

  @GetMapping(value = "/baseSearch/yearLessThanEqual", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ApiResponseDto> getFilmsByYearLessThanEqual(@RequestParam String year) {
    return service.findFilmByYearLessThanEqual(year);
  }

  @GetMapping(value = "/user/new")
  public void createUser(@RequestParam String username, @RequestParam String password) {
    service.createUser(username, password);
  }

  @GetMapping(value = "/user/addToWatched")
  public void addToWatched(@RequestParam String username, @RequestParam String film_id) {
    service.addToWatched(username, film_id);
  }

  @GetMapping(value = "/user/getWatchlist")
  public List<ApiResponseDto> getWatchlist(@RequestParam String username) {
    return service.findWatchListByUsername(username);
  }
}

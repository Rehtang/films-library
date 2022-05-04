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
}

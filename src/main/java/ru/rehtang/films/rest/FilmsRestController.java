package ru.rehtang.films.rest;
//Rehtang
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.rehtang.films.dto.ApiResponseDto;
import ru.rehtang.films.dto.RequestDto;
import ru.rehtang.films.service.FilmsProviderService;

@RestController
@RequiredArgsConstructor
public class FilmsRestController {
  private final FilmsProviderService service;

  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ApiResponseDto getFilms(@RequestBody RequestDto requestDto) {
    return service.receiveFilms(requestDto.getIMdbId(), requestDto.getPlot());
  }
}


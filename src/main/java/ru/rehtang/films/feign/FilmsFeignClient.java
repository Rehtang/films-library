package ru.rehtang.films.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rehtang.films.dto.ApiResponseDto;
import ru.rehtang.films.dto.ApiSearchResponse;
import ru.rehtang.films.dto.FilmType;

@FeignClient(name = "filmFeignClient", url = "${filmsApi.feign.url}")
public interface FilmsFeignClient {

  @GetMapping("/")
  ApiResponseDto receiveFilms(
      @RequestParam("i") String id,
      @RequestParam("type") FilmType type,
      @RequestParam("y") String year,
      @RequestParam("apikey") String apiKey);

  @GetMapping("/")
  ApiSearchResponse receiveFilmByTitle(
          @RequestParam("s") String title,
          @RequestParam("type") FilmType type,
          @RequestParam("y") String year,
          @RequestParam("page") Integer page,
          @RequestParam("apikey") String apiKey
  );
}

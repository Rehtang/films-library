package ru.rehtang.films.feign;
//Rehtang
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rehtang.films.dto.ApiResponseDto;

@FeignClient(name = "filmFeignClient", url = "${filmsApi.feign.url}")
public interface FilmsFeignClient {

  @GetMapping("/")
  ApiResponseDto receiveFilms(
      @RequestParam("i") String id,
      @RequestParam("plot") String plot,
      @RequestParam("apikey") String apiKey);
}

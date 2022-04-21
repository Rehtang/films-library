package ru.rehtang.films.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.rehtang.films.dto.ApiResponseDto;
import ru.rehtang.films.feign.FilmsFeignClient;
import ru.rehtang.films.mapper.FilmMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmsProviderService {

  private final FilmsFeignClient client;
  private final FilmMapper mapper;

  @Value("${filmsApi.feign.apiKey}")
  private String filmApiKey;

  public ApiResponseDto receiveFilms(String iMdbId, String plot) {
    var res = client.receiveFilms(iMdbId, plot, filmApiKey);
    log.info("{}", res);
    System.out.println(mapper.toEntity(res));
    return res;
  }

}

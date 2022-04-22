package ru.rehtang.films.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.rehtang.films.dto.ApiResponseDto;
import ru.rehtang.films.entity.Film;
import ru.rehtang.films.feign.FilmsFeignClient;
import ru.rehtang.films.mapper.FilmMapper;
import ru.rehtang.films.repository.FilmRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmsProviderService {

  private final FilmsFeignClient client;
  private final FilmMapper mapper;
  private final FilmRepository filmRepository;

  @Value("${filmsApi.feign.apiKey}")
  private String filmApiKey;

  public ApiResponseDto receiveFilms(String iMdbId, String plot) {
    var res = client.receiveFilms(iMdbId, plot, filmApiKey);
    log.info("{}", res);
    var rese = mapper.toEntity(res);
    System.out.println(mapper.toEntity(res));
    deleteById(iMdbId);
    save(rese);
    return res;
  }

  public void save(Film film) {
    filmRepository.save(film);
  }

  public void deleteById(String id) {
    filmRepository.deleteById(id);
  }
  public void saveAndFlush(Film film){
    filmRepository.saveAndFlush(film);
  }
}

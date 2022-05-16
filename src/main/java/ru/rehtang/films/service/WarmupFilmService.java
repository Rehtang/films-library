package ru.rehtang.films.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.rehtang.films.feign.FilmsFeignClient;
import ru.rehtang.films.mapper.FilmMapper;
import ru.rehtang.films.persistence.repository.FilmReceiveRepository;
import ru.rehtang.films.persistence.repository.FilmRepository;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class WarmupFilmService {

  private final FilmsFeignClient filmsFeignClient;
  private final FilmMapper mapper;
  private final FilmRepository filmRepository;
  private final FilmReceiveRepository receiveRepository;

  @Value("${filmsApi.feign.apiKey}")
  private String apiKey;

  @PostConstruct
  public void warmUp() {
    var filmsForReceived = receiveRepository.findFilmReceivesByIsReceivedFalse();

    if (CollectionUtils.isEmpty(filmsForReceived)) {
      log.info("All films was received");
    }

    filmsForReceived.forEach(
        o -> {
          var imdbId = o.getMovieId();
          var receiveMovieDto = filmsFeignClient.receiveFilms(imdbId, null, null, apiKey);
          var entity = mapper.toEntity(receiveMovieDto);
          filmRepository.save(entity);

          o.setIsReceived(true);
          receiveRepository.save(o);
          log.info("Film with imdbId: {} was received", imdbId);
        });
  }
}


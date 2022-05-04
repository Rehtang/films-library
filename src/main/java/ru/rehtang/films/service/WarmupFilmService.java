package ru.rehtang.films.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.rehtang.films.feign.FilmsFeignClient;
import ru.rehtang.films.mapper.FilmMapper;
import ru.rehtang.films.repository.FilmReceiveRepository;
import ru.rehtang.films.repository.FilmRepository;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class WarmupFilmService {

  private final FilmsFeignClient filmsFeignClient;
  private final FilmMapper mapper;
  private final FilmRepository repos;
  private final FilmReceiveRepository receiveRepos;

  @Value("${filmsApi.feign.apiKey}")
  private String apiKey;

  @PostConstruct
  public void warmUp() {
    var filmsForReceived = receiveRepos.findAll();

    if (CollectionUtils.isEmpty(filmsForReceived)) {
      throw new RuntimeException("film_receive table is empty");
    }

    filmsForReceived.forEach(
        o -> {
          if (!o.getIsReceived()) {
            var movieId = o.getMovieId();

            var receivedMovieDto = filmsFeignClient.receiveFilms(movieId, null, null, apiKey);
            var entity = mapper.toEntity(receivedMovieDto);
            repos.save(entity);

            o.setIsReceived(true);
            receiveRepos.save(o);
          }
        });
  }
}

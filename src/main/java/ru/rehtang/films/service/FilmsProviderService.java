package ru.rehtang.films.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.rehtang.films.dto.ApiResponseDto;
import ru.rehtang.films.dto.FilmType;
import ru.rehtang.films.feign.FilmsFeignClient;
import ru.rehtang.films.mapper.FilmMapper;
import ru.rehtang.films.repository.FilmRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmsProviderService {

  private final FilmsFeignClient client;
  private final FilmMapper mapper;
  private final FilmRepository filmRepository;

  @Value("${filmsApi.feign.apiKey}")
  private String filmApiKey;

  public ApiResponseDto receiveFilms(String iMdbId, FilmType filmType, String year) {
    var filmFromBase = filmRepository.findById(iMdbId);
    if (filmFromBase.isPresent()) {
      return mapper.toDto(filmFromBase.get());
    }

    var fromApi = client.receiveFilms(iMdbId, filmType, year, filmApiKey);
    filmRepository.save(mapper.toEntity(fromApi));
    return fromApi;
  }

  public List<ApiResponseDto> receiveFilmsByTitle(
      String title, FilmType type, String year, Integer page) {
    var filmFromBase = filmRepository.findAllByTitle(title);
    if (!CollectionUtils.isEmpty(filmFromBase)) {
      return filmFromBase.stream().map(mapper::toDto).collect(Collectors.toList());
    }
    var filmFromApi = client.receiveFilmByTitle(title, null, null, page, filmApiKey);
    return filmFromApi.getResult().stream()
        .map(
            o -> {
              var filmId = o.getImdbId();
              var filmInBase = filmRepository.findById(filmId);
              if (filmInBase.isPresent()) {
                return mapper.toDto(filmInBase.get());
              }
              var fromApi = client.receiveFilms(filmId, type, year, filmApiKey);
              filmRepository.save(mapper.toEntity(fromApi));
              return fromApi;
            })
        .collect(Collectors.toList());
  }
}
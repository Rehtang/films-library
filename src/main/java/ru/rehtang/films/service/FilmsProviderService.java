package ru.rehtang.films.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.rehtang.films.dto.ApiResponseDto;
import ru.rehtang.films.dto.FilmType;
import ru.rehtang.films.feign.FilmsFeignClient;
import ru.rehtang.films.mapper.FilmMapper;
import ru.rehtang.films.persistence.entity.Film;
import ru.rehtang.films.persistence.repository.FilmRepository;
import ru.rehtang.films.persistence.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmsProviderService {

  private final FilmsFeignClient client;
  private final FilmMapper mapper;
  private final FilmRepository filmRepository;
  private final UserRepository userRepository;

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

  public List<ApiResponseDto> findFilmByYear(String year) {
    var filmInBase = filmRepository.findAllByYear(year);

    return filmInBase.stream().map(mapper::toDto).collect(Collectors.toList());
  }

  public List<ApiResponseDto> findFilmByYearGreaterThanEqual(String year) {
    var filmInBase = filmRepository.findAllByYearGreaterThanEqual(year);

    return filmInBase.stream().map(mapper::toDto).collect(Collectors.toList());
  }

  public List<ApiResponseDto> findFilmByYearLessThanEqual(String year) {
    var filmInBase = filmRepository.findAllByYearLessThanEqual(year);

    return filmInBase.stream().map(mapper::toDto).collect(Collectors.toList());
  }

  public Film findById(String id) {
    return filmRepository
        .findById(id)
        .orElseThrow(
            () ->
                new RuntimeException(
                    String.format("There is no film with ID = %s in Database", id)));
  }

  public void deleteById(String id) {
    var entity = findById(id);
    filmRepository.delete(entity);
  }

  public void addToFavourite(String username, String film_id) {
    var userInBase = userRepository.findUserByUsername(username);
    var filmInBase = filmRepository.findById(film_id);
    filmInBase.get().addUserToFilm(userInBase.get());
    userInBase.get().addToFavourite(filmInBase.get());
    userRepository.save(userInBase.get());
  }

  public void save(Film film) {
    filmRepository.save(film);
  }

  public List<ApiResponseDto> findWatchListByUsername(String username) {
    var userInBase = userRepository.findUserByUsername(username);
    return userInBase.get().getFavouriteFilms().stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }
}
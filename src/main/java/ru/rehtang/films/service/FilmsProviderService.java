package ru.rehtang.films.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.rehtang.films.dto.ApiResponseDto;
import ru.rehtang.films.dto.FilmType;
import ru.rehtang.films.entity.User;
import ru.rehtang.films.feign.FilmsFeignClient;
import ru.rehtang.films.mapper.FilmMapper;
import ru.rehtang.films.repository.FilmRepository;
import ru.rehtang.films.repository.UserRepository;

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

  public void createUser(String username, String password) {
    var userInBase = new User();
    userInBase.setUsername(username);
    userInBase.setPassword(password);
    userRepository.save(userInBase);
  }

  public void addToWatched(String username, String film_id) {
    var userInBase = userRepository.findUserByUsername(username);
    var filmInBase = filmRepository.findById(film_id);
    filmInBase.get().addUserToFilm(userInBase);
    userInBase.addFilmToUser(filmInBase.get());
    userRepository.save(userInBase);
  }

  public List<ApiResponseDto> findWatchListByUsername(String username) {
    var userInBase = userRepository.findUserByUsername(username);
    return userInBase.getFavouriteFilms().stream().map(mapper::toDto).collect(Collectors.toList());
  }
}
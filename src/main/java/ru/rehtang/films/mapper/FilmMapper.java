package ru.rehtang.films.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.CollectionUtils;
import ru.rehtang.films.dto.ApiResponseDto;
import ru.rehtang.films.dto.RatingsDto;
import ru.rehtang.films.entity.Film;
import ru.rehtang.films.entity.FilmRating;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FilmMapper {

  @Mapping(target = "ageRate", source = "dto.ageRate")
  @Mapping(target = "awards", source = "dto.awards")
  @Mapping(target = "ratings", expression = "java(toEntities(dto))")
  public abstract Film toEntity(ApiResponseDto dto);

  @Mapping(target = "film", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "filmId", source = "filmDto.imdbID")
  @Mapping(target = "source", source = "dto.source")
  @Mapping(target = "value", source = "dto.value")
  public abstract FilmRating toEntity(RatingsDto dto, ApiResponseDto filmDto);

  public List<FilmRating> toEntities(ApiResponseDto film) {
    var list = film.getRatings();
    if (CollectionUtils.isEmpty(list)) {
      return Collections.emptyList();
    }
    return list.stream().map(o -> toEntity(o, film)).collect(Collectors.toList());
  }

  @Mapping(target = "ratings", expression = "java(toDtoRatings(film))")
  public abstract ApiResponseDto toDto(Film film);

  public List<RatingsDto> toDtoRatings(Film filmEntity) {
    var list = filmEntity.getRatings();
    if (CollectionUtils.isEmpty(list)) {
      return Collections.emptyList();
    }
    return list.stream().map(this::toDto).collect(Collectors.toList());
  }

  public abstract RatingsDto toDto(FilmRating entity);
}

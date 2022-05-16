package ru.rehtang.films.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rehtang.films.dto.BasicResponse;
import ru.rehtang.films.dto.FavouriteDto;
import ru.rehtang.films.service.FavouriteService;

@Validated
@RestController
@RequestMapping("/favourite")
@RequiredArgsConstructor
public class FavouriteController {

  private final FavouriteService favouriteService;

  @PostMapping("/")
  public BasicResponse<Void> addFavourite(@RequestBody FavouriteDto dto) {
    favouriteService.addFavourite(dto);
    return new BasicResponse<>();
  }

  @DeleteMapping("/")
  public BasicResponse<Void> removeFromFavourite(@RequestBody FavouriteDto dto) {
    favouriteService.removeFavourite(dto);
    return new BasicResponse<>();
  }
}
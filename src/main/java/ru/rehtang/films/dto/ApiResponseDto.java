package ru.rehtang.films.dto;
//Rehtang
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseDto {
  @JsonProperty("Title")
  private String title;

  @JsonProperty("Year")
  private String year;

  @JsonProperty("Rated")
  private String ageRate; // TODO

  @JsonProperty("Released")
  private String releaseYear;

  @JsonProperty("Runtime")
  private String filmTime;

  @JsonProperty("Genre")
  private String genres;

  @JsonProperty("Director")
  private String director;

  @JsonProperty("Writer")
  private String writers;

  @JsonProperty("Actors")
  private String actors;

  @JsonProperty("Plot")
  private String plot;

  @JsonProperty("Language")
  private String language;

  @JsonProperty("Country")
  private String country;

  @JsonProperty("Awards")
  private String awards;

  @JsonProperty("Poster")
  private String posterUrl;

  @JsonProperty("Ratings")
  private List<RatingsDto> ratings;

  @JsonProperty("Metascore")
  private String metascore;

  private String imdbRating;
  private String imdbVotes;
  private String imdbID;

  @JsonProperty("Type")
  private String type;

  @JsonProperty("DVD")
  private String dvd;

  @JsonProperty("BoxOffice")
  private String boxOffice;

  @JsonProperty("Production")
  private String production;

  @JsonProperty("Website")
  private String website;

  @JsonProperty("Response")
  private Boolean response;
 }
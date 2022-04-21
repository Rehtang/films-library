package ru.rehtang.films.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film")
public class Film {

  @Column(name = "title")
  private String title;

  @Column(name = "year_of_release")
  private String year;

  @Column(name = "released")
  private String releaseYear;

  @Column(name = "runtime")
  private String filmTime;

  @Column(name = "genre")
  private String genres;

  @Column(name = "director")
  private String director;

  @Column(name = "writer")
  private String writers;

  @Column(name = "actor")
  private String actors;

  @Column(name = "plot")
  private String plot;

  @Column(name = "language_of")
  private String language;

  @Column(name = "country")
  private String country;

  @Column(name = "poster")
  private String posterUrl;

  @Column(name = "metascore")
  private String metascore;

  @Column(name = "imdb_rating")
  private String imdbRating;

  @Column(name = "imdb_vote")
  private String imdbVotes;

  @Id
  @Column(name = "imdb_id")
  private String imdbID;

  @Column(name = "type")
  private String type;

  @Column(name = "dvd")
  private String dvd;

  @Column(name = "box_office")
  private Double boxOffice;

  @Column(name = "production")
  private String production;

  @Column(name = "website")
  private String website;

  @Column(name = "response")
  private Boolean response;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "film_id")
  private List<FilmRating> ratings;
}

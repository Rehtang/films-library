package ru.rehtang.films.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film")
public class Film {

  @Id
  @Column(name = "imdb_id")
  private String imdbID;

  @Column(name = "title")
  private String title;

  @Column(name = "year_of_release")
  private String year;

  @Column(name = "rated")
  private String ageRate;

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

  @Column(name = "award")
  private String awards;

  @Column(name = "poster")
  private String posterUrl;

  @Column(name = "metascore")
  private String metascore;

  @Column(name = "imdb_rating")
  private String imdbRating;

  @Column(name = "imdb_vote")
  private String imdbVotes;

  @Column(name = "type")
  private String type;

  @Column(name = "dvd")
  private String dvd;

  @Column(name = "box_office")
  private String boxOffice;

  @Column(name = "production")
  private String production;

  @Column(name = "website")
  private String website;

  @Column(name = "response")
  private Boolean response;

  @ToString.Exclude
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "film")
  private List<FilmRating> ratings;

  @JsonBackReference
  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "favouriteFilms")
  private List<User> users;

  public Film addUserToFilm(User user) {
    if (users == null) {
      users = new ArrayList<>();
    }
    users.add(user);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Film film = (Film) o;
    return imdbID != null && Objects.equals(imdbID, film.imdbID);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
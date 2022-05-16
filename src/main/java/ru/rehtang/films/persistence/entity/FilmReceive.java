package ru.rehtang.films.persistence.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film_receive")
public class FilmReceive {

  @Id
  @Column(name = "movie_id")
  private String movieId;

  @Column(name = "received")
  private Boolean isReceived;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    FilmReceive that = (FilmReceive) o;
    return movieId != null && Objects.equals(movieId, that.movieId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

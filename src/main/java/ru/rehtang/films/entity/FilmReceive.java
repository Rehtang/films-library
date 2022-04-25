package ru.rehtang.films.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
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
}

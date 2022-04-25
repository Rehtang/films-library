package ru.rehtang.films.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film_rating")
public class FilmRating {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "film_id")
  private String filmId;

  @Column(name = "source")
  private String source;

  @Column(name = "value")
  private String value;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "film_id", insertable = false,updatable = false)
  private Film film;
}

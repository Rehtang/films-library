package ru.rehtang.films.entity;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
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
  @JoinColumn(name = "film_id", insertable = false, updatable = false)
  private Film film;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Film film = (Film) o;
    return getId() != null && Objects.equals(getId(), film.getImdbID());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

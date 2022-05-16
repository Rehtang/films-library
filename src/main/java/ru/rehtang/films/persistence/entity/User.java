package ru.rehtang.films.persistence.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private UUID uuid;

  @Column(name = "username")
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, columnDefinition = "ACTIVE")
  private Status status;

  public enum Status {
    ACTIVE,
    BANNED,
    DELETED
  }

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "watch_list",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "film_id"))
  private List<Film> favouriteFilms;

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<RoleEntity> roles = new HashSet<>();

  public User addToFavourite(Film film) {
    if (favouriteFilms == null) {
      favouriteFilms = new ArrayList<>();
    }
    favouriteFilms.add(film);
    return this;
  }

  public User removeFromFavourite(Film film) {
    if (CollectionUtils.isEmpty(favouriteFilms)) {
      return this;
    }
    favouriteFilms.remove(film);
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
    User user = (User) o;
    return uuid != null && Objects.equals(uuid, user.uuid);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
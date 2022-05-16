package ru.rehtang.films.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rehtang.films.persistence.entity.Film;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, String> {
  List<Film> findAllByTitle(String title);

  List<Film> findAllByYear(String year);

  List<Film> findAllByYearLessThanEqual(String year);

  List<Film> findAllByYearGreaterThanEqual(String year);
}

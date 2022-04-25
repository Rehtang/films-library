package ru.rehtang.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rehtang.films.entity.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, String> {}

package ru.rehtang.films.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rehtang.films.entity.FilmReceive;

@Repository
public interface FilmReceiveRepos extends JpaRepository<FilmReceive, String> {}

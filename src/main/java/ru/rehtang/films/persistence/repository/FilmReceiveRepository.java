package ru.rehtang.films.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rehtang.films.persistence.entity.FilmReceive;

import java.util.List;

@Repository
public interface FilmReceiveRepository extends JpaRepository<FilmReceive, String> {
  List<FilmReceive> findFilmReceivesByIsReceivedFalse();
}

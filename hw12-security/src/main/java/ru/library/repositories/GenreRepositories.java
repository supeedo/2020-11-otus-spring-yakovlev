package ru.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.library.models.Genre;

@Repository
public interface GenreRepositories extends JpaRepository<Genre, Long> {
}

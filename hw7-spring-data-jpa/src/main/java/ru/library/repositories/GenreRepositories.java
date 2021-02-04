package ru.library.repositories;

import ru.library.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepositories extends JpaRepository<Genre, Long > {
}

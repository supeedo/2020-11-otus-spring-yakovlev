package library.repositories;

import library.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepositories extends JpaRepository<Genre, Long > {
}

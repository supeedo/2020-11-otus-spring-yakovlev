package library.repositories;


import library.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepositories extends JpaRepository<Author, Long > {
    List<Author> findByFullNameContains(String authorName);
}

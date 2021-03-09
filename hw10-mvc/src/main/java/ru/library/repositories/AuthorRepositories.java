package ru.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.library.models.Author;

import java.util.Optional;

@Repository
public interface AuthorRepositories extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByFullName(String fullName);
}

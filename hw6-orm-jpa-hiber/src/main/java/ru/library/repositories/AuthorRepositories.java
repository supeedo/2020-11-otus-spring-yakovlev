package ru.library.repositories;

import ru.library.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositories {
    Long getAuthorsCount();

    Author insertAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthorById(long authorId);

    Optional<Author> getAuthorById(long authorId);

    List<Author> getAllAuthors();

    List<String> getTitles();
}

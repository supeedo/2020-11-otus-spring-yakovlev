package ru.library.dao;

import ru.library.dto.Author;

import java.util.List;

public interface AuthorDao {
    int getAuthorsCount();

    void insertAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthorById(long authorId);

    Author getAuthorById(long authorId);

    List<Author> getAllAuthors();

    List<String> getTitles();
}

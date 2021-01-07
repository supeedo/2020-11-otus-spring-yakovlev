package ru.library.dao;

import ru.library.dto.Author;

import java.util.List;

public interface AuthorDao {
    int authorsCount();
    void insert(Author author);
    void update(Author author);
    void deleteByAuthorId(long authorId);
    Author getByAuthorId(long authorId);
    List<Author> getAllAuthors();
}

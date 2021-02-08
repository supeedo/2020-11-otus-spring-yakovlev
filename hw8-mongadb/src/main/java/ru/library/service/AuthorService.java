package ru.library.service;

import ru.library.models.Author;

import java.util.List;

public interface AuthorService {
    String getCount();

    String getAllAuthors();

    String getAuthorById(String authorId);

    String deleteAuthorById(String authorId);

    String getAuthorsByName(String authorName);

    String createNewAuthor(String authorFullName);

    String updateAuthor(String authorId, String authorFullName);

    List<List<String>> prepareForTable(List<Author> genres);
}

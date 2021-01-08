package ru.library.service;

import ru.library.dto.Author;

import java.util.List;

public interface AuthorService {
    String getCount();

    String getAllAuthors();

    String getAuthorById(Long authorId);

    String deleteAuthorById(Long authorId);

    String createNewAuthor(Long authorId, String authorFullName);

    String updateAuthor(Long authorId, String authorFullName);

    List<List<String>> prepareForTable(List<Author> genres);
}

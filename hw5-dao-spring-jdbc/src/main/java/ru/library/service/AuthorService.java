package ru.library.service;

import ru.library.dto.Author;

import java.util.List;

public interface AuthorService {
    int getCount();

    String getAllAuthors();

    String getAuthorById(Long authorId);

    String deleteAuthorById(Long authorId);

    String createNewAuthor(Long id, String authorFullName);

    String updateAuthor(Long id, String authorFullName);

    List<List<String>> prepareForTable(List<Author> genres);
}

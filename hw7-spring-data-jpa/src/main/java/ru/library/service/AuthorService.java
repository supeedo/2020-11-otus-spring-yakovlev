package ru.library.service;


import ru.library.models.Author;

import java.util.List;

public interface AuthorService {
    String getCount();

    String getAllAuthors();

    String getAuthorById(long authorId);

    String deleteAuthorById(long authorId);

    String getAuthorsByName(String authorName);

    String createNewAuthor(String authorFullName);

    String updateAuthor(long authorId, String authorFullName);

    List<List<String>> prepareForTable(List<Author> genres);
}

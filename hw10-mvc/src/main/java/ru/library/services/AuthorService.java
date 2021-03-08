package ru.library.services;

import ru.library.Dto.AuthorDto;
import ru.library.models.Author;

import java.util.List;

public interface AuthorService {
    String getCount();

    List<AuthorDto> getAllAuthors();

    Author getAuthorById(long authorId);

    Author deleteAuthorById(long authorId);

    String getAuthorsByName(String authorName);

    String createNewAuthor(String authorFullName);

    String updateAuthor(Long authorId, String authorFullName);

    Author save (Author author);
}

package ru.library.services;

import ru.library.Dto.AuthorDto;
import ru.library.models.Author;

import java.util.List;

public interface AuthorService {
    long getCount();

    List<AuthorDto> getAllAuthors();

    AuthorDto getAuthorById(long authorId);

    void deleteAuthorById(AuthorDto authorDto);

    AuthorDto getAuthorsByName(String authorName);

    void createNewAuthor(AuthorDto authorDto);

    void updateAuthor(AuthorDto authorDto);

    Author save (Author author);
}

package ru.library.services;

import ru.library.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    long getCount();

    List<AuthorDto> getAllAuthors();

    AuthorDto getAuthorById(long authorId);

    void deleteAuthorById(long authorId);

    AuthorDto getAuthorsByName(String authorName);

    void createNewAuthor(AuthorDto authorDto);

    void updateAuthor(AuthorDto authorDto);

    void save (AuthorDto authorDto);
}

package ru.library.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.Dto.AuthorDto;
import ru.library.Dto.AuthorMapper;
import ru.library.Dto.GenreMapper;
import ru.library.models.Author;
import ru.library.repositories.AuthorRepositories;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositories authorRepositories;

    public AuthorServiceImpl(AuthorRepositories authorRepositories) {
        this.authorRepositories = authorRepositories;
    }

    @Transactional(readOnly = true)
    @Override
    public String getCount() {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepositories.findAll();
        return authors.stream().map(AuthorMapper.INSTANCE::authorToAuthorDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Author getAuthorById(long authorId) {
        return authorRepositories.findById(authorId).orElseThrow(()->new IllegalArgumentException("Author not found"));
    }

    @Transactional
    @Override
    public Author deleteAuthorById(long authorId) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public String getAuthorsByName(String authorName) {
        return null;
    }

    @Transactional
    @Override
    public String createNewAuthor(String authorFullName) {
        return null;
    }

    @Transactional
    @Override
    public String updateAuthor(Long authorId, String authorFullName) {
        return null;
    }

    @Transactional
    @Override
    public Author save(Author author) {
        return authorRepositories.save(author);
    }
}

package ru.library.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.Dto.AuthorDto;
import ru.library.Dto.AuthorMapper;
import ru.library.models.Author;
import ru.library.repositories.AuthorRepositories;

import javax.persistence.EntityNotFoundException;
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
    public long getCount() {
        return authorRepositories.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<AuthorDto> getAllAuthors() {
        final List<Author> authors = authorRepositories.findAll();
        return authors.stream().map(AuthorMapper.INSTANCE::authorToAuthorDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public AuthorDto getAuthorById(long authorId) {
        final Author author = authorRepositories.findById(authorId).orElseThrow(() -> new EntityNotFoundException("Author not found"));
        return AuthorMapper.INSTANCE.authorToAuthorDto(author);
    }

    @Transactional
    @Override
    public void deleteAuthorById(long authorId) {
        authorRepositories.deleteById(authorId);
    }

    @Transactional(readOnly = true)
    @Override
    public AuthorDto getAuthorsByName(String authorName) {
        final Author author = authorRepositories.findAuthorByFullName(authorName).orElseThrow(() -> new EntityNotFoundException("Author not found"));
        return AuthorMapper.INSTANCE.authorToAuthorDto(author);
    }

    @Transactional
    @Override
    public void createNewAuthor(AuthorDto authorDto) {
        final Author author = AuthorMapper.INSTANCE.authorDtoToAuthor(authorDto);
        authorRepositories.save(author);
    }

    @Transactional
    @Override
    public void updateAuthor(AuthorDto authorDto) {
        final Author author = authorRepositories.findById(authorDto.getId()).orElseThrow(() -> new IllegalArgumentException("Author not found"));
        author.setFullName(authorDto.getFullName());
    }

    @Transactional
    @Override
    public void save(AuthorDto authorDto) {
        final Author author = AuthorMapper.INSTANCE.authorDtoToAuthor(authorDto);
        authorRepositories.save(author);
    }
}

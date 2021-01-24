package ru.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Author;
import ru.library.repositories.AuthorRepositories;
import ru.library.utils.TableRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositories authorDao;
    private final TableRenderer renderer;

    public AuthorServiceImpl(AuthorRepositories authorDao, TableRenderer renderer) {
        this.authorDao = authorDao;
        this.renderer = renderer;
    }

    @Transactional(readOnly = true)
    @Override
    public String getCount() {
        return String.format("There are %s authors in the library", authorDao.getAuthorsCount());
    }

    @Transactional(readOnly = true)
    @Override
    public String getAllAuthors() {
        return renderer.tableRender(authorDao.getTitles(), prepareForTable(authorDao.getAllAuthors()));
    }

    @Transactional(readOnly = true)
    @Override
    public String getAuthorById(long authorId) {
        Optional<Author> author = authorDao.getAuthorById(authorId);
        if (author.isPresent()) {
            return renderer.tableRender(authorDao.getTitles(),
                    prepareForTable(List.of(author.get())));
        } else {
            return String.format("Author with id: %d, not found!", authorId);
        }
    }

    @Transactional
    @Override
    public String deleteAuthorById(long authorId) {
        authorDao.deleteAuthorById(authorId);
        return String.format("Author with id: %s has delete", authorId);
    }

    @Transactional
    @Override
    public String createNewAuthor(String authorFullName) {
        authorDao.insertAuthor(new Author(0L, authorFullName));
        return "Author has insert";
    }

    @Transactional
    @Override
    public String updateAuthor(long authorId, String authorFullName) {
        authorDao.updateAuthorById(new Author(authorId, authorFullName));
        Optional<Author> author = authorDao.getAuthorById(authorId);
        if (author.isPresent()) {
            return String.format("Author:\n%s \nhas update", renderer.tableRender(authorDao.getTitles(),
                    prepareForTable(List.of(author.get()))));
        } else {
            return String.format("Author with id: %d, not found!", authorId);
        }
    }

    @Override
    public List<List<String>> prepareForTable(List<Author> authors) {
        List<List<String>> tablePresentation = new ArrayList<>();
        for (Author author : authors) {
            List<String> columnList = new ArrayList<>();
            columnList.add(String.valueOf(author.getId()));
            columnList.add(author.getFullName());
            tablePresentation.add(columnList);
        }
        return tablePresentation;
    }
}

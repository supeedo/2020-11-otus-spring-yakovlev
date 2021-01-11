package ru.library.service;

import org.springframework.stereotype.Service;
import ru.library.dao.AuthorDao;
import ru.library.dto.Author;
import ru.library.utils.TableRenderer;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final TableRenderer renderer;

    public AuthorServiceImpl(AuthorDao authorDao, TableRenderer renderer) {
        this.authorDao = authorDao;
        this.renderer = renderer;
    }

    @Override
    public String getCount() {
        return String.format("There are %s authors in the library", authorDao.getAuthorsCount());
    }

    @Override
    public String getAllAuthors() {
        return renderer.tableRender(authorDao.getTitles(), prepareForTable(authorDao.getAllAuthors()));
    }

    @Override
    public String getAuthorById(Long authorId) {
        return renderer.tableRender(authorDao.getTitles(),
                prepareForTable(List.of(authorDao.getAuthorById(authorId))));
    }

    @Override
    public String deleteAuthorById(Long authorId) {
        authorDao.deleteAuthorById(authorId);
        return String.format("Author with id: %s has delete", authorId);
    }

    @Override
    public String createNewAuthor(Long authorId, String authorFullName) {
        authorDao.insertAuthor(new Author(authorId, authorFullName));
        return String.format("Author:\n%s \nhas insert", renderer.tableRender(authorDao.getTitles(),
                prepareForTable(List.of(authorDao.getAuthorById(authorId)))));
    }

    @Override
    public String updateAuthor(Long authorId, String authorFullName) {
        authorDao.updateAuthor(new Author(authorId, authorFullName));
        return String.format("Genre:\n%s \nhas update", renderer.tableRender(authorDao.getTitles(),
                prepareForTable(List.of(authorDao.getAuthorById(authorId)))));
    }

    @Override
    public List<List<String>> prepareForTable(List<Author> authors) {
        List<List<String>> tablePresentation = new ArrayList<>();
        for (Author author : authors) {
            List<String> columnList = new ArrayList<>();
            columnList.add(author.getId().toString());
            columnList.add(author.getFullName());
            tablePresentation.add(columnList);
        }
        return tablePresentation;
    }
}

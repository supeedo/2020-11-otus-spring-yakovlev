package ru.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.repositories.AuthorRepositories;
import ru.library.models.Author;
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
    public String getAuthorById(Long authorId) {
        Optional<Author> author = authorDao.getAuthorById(authorId);
        if(author.isPresent()) {
            return renderer.tableRender(authorDao.getTitles(),
                    prepareForTable(List.of(author.get())));
        }else {
            return String.format("Автор с id: %d, не найден!", authorId);
        }
    }

    @Transactional
    @Override
    public String deleteAuthorById(Long authorId) {
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
    public String updateAuthor(Long authorId, String authorFullName) {
        authorDao.updateAuthor(new Author(authorId, authorFullName));
        return String.format("Genre:\n%s \nhas update", renderer.tableRender(authorDao.getTitles(),
                prepareForTable(List.of((authorDao.getAuthorById(authorId)).get()))));
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

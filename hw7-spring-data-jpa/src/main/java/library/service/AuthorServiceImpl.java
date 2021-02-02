package library.service;

import library.models.Author;
import library.repositories.AuthorRepositories;
import library.utils.TableRenderer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHOR_WITH_ID_D_NOT_FOUND = "Author with id: %d, not found!";
    private static final String AUTHOR_WITH_ID_S_HAS_DELETE = "Author with id: %s has delete";
    private static final String AUTHOR_S_HAS_UPDATE = "Author:\n%s \nhas update";
    private static final String AUTHOR_HAS_INSERT = "Author has insert";
    private static final String THERE_ARE_S_AUTHORS_IN_THE_LIBRARY = "There are %s authors in the library";
    private final AuthorRepositories authorDao;
    private final TableRenderer renderer;

    public AuthorServiceImpl(AuthorRepositories authorDao, TableRenderer renderer) {
        this.authorDao = authorDao;
        this.renderer = renderer;
    }

    @Transactional(readOnly = true)
    @Override
    public String getCount() {
        return String.format(THERE_ARE_S_AUTHORS_IN_THE_LIBRARY, authorDao.count());
    }

    @Transactional(readOnly = true)
    @Override
    public String getAllAuthors() {
        return renderer.tableRender(getTitles(), prepareForTable(authorDao.findAll()));
    }

    @Transactional(readOnly = true)
    @Override
    public String getAuthorById(long authorId) {
        Optional<Author> author = authorDao.findById(authorId);
        if (author.isPresent()) {
            return renderer.tableRender(getTitles(),
                    prepareForTable(List.of(author.get())));
        } else {
            return String.format(AUTHOR_WITH_ID_D_NOT_FOUND, authorId);
        }
    }

    @Transactional
    @Override
    public String deleteAuthorById(long authorId) {
        Optional<Author> author = authorDao.findById(authorId);
        if (author.isPresent()) {
            authorDao.delete(author.get());
            return String.format(AUTHOR_WITH_ID_S_HAS_DELETE, authorId);
        } else {
            return String.format(AUTHOR_WITH_ID_D_NOT_FOUND, authorId);
        }
    }

    @Transactional
    @Override
    public String createNewAuthor(String authorFullName) {
        authorDao.save(new Author(0L, authorFullName));
        return AUTHOR_HAS_INSERT;
    }

    @Transactional
    @Override
    public String updateAuthor(long authorId, String authorFullName) {
        Optional<Author> author = authorDao.findById(authorId);
        if(author.isPresent()) {
            author.get().setFullName(authorFullName);
            Optional<Author> updateAuthor = authorDao.findById(authorId);
            if (updateAuthor.isPresent()) {
                return String.format(AUTHOR_S_HAS_UPDATE, renderer.tableRender(getTitles(),
                        prepareForTable(List.of(updateAuthor.get()))));
            } else {
                return String.format(AUTHOR_WITH_ID_D_NOT_FOUND, authorId);
            }
        }else{
            return String.format(AUTHOR_WITH_ID_D_NOT_FOUND, authorId);
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

    public List<String> getTitles() {
        return List.of("id", "Author full name");
    }
}

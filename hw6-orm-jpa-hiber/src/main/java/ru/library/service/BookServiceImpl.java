package ru.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Author;
import ru.library.models.Book;
import ru.library.models.Genre;
import ru.library.repositories.BookRepositories;
import ru.library.utils.TableRenderer;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositories bookDao;
    private final TableRenderer renderer;

    public BookServiceImpl(BookRepositories bookDao, TableRenderer renderer) {
        this.bookDao = bookDao;
        this.renderer = renderer;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCount() {
        return bookDao.getBooksCount();
    }

    @Transactional(readOnly = true)
    @Override
    public String getAllBooks() {
        return renderer.tableRender(bookDao.getTitles(), prepareForTable(bookDao.getAllBooks()));
    }

    @Transactional(readOnly = true)
    @Override
    public String getBookById(Long id) {
        return renderer.tableRender(bookDao.getTitles(), prepareForTable(List.of((bookDao.getBookById(id)).get())));
    }

    @Transactional
    @Override
    public String deleteBookById(Long id) {
        bookDao.deleteBookById(id);
        return String.format("Book with id: %s has delete", id);
    }

    @Transactional
    @Override
    public String createNewBook(Long id, String bookName, Long authorId, Long genreId) {
        bookDao.insertBook(new Book(id, bookName, new Author(authorId, null), new Genre(genreId, null)));

        return String.format("Book:\n%s \nhas insert",
                renderer.tableRender(bookDao.getTitles(),
                        prepareForTable(List.of((bookDao.getBookById(id)).get()))));
    }

    @Transactional
    @Override
    public String updateBook(Long id, String bookName, Long authorId, Long genreId) {
        bookDao.insertBook(new Book(id, bookName, new Author(authorId, null), new Genre(genreId, null)));
        return String.format("Book:\n%s \nhas update", renderer.tableRender(bookDao.getTitles(),
                prepareForTable(List.of((bookDao.getBookById(id)).get()))));
    }

    @Override
    public List<List<String>> prepareForTable(List<Book> books) {
        List<List<String>> tablePresentation = new ArrayList<>();
        for (Book book : books) {
            List<String> columnList = new ArrayList<>();
            columnList.add(book.getId().toString());
            columnList.add(book.getBookTitle());
            columnList.add(book.getAuthor().getFullName());
            columnList.add(book.getGenre().getGenreName());
            tablePresentation.add(columnList);
        }
        return tablePresentation;
    }
}

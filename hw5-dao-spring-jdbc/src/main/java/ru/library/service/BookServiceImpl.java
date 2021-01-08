package ru.library.service;

import org.springframework.stereotype.Service;
import ru.library.dao.BookDao;
import ru.library.dto.Author;
import ru.library.dto.Book;
import ru.library.dto.Genre;
import ru.library.utils.TableRenderer;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final TableRenderer renderer;

    public BookServiceImpl(BookDao bookDao, TableRenderer renderer) {
        this.bookDao = bookDao;
        this.renderer = renderer;
    }

    @Override
    public int getCount() {
        return bookDao.getBooksCount();
    }

    @Override
    public String getAllBooks() {
        return renderer.tableRender(bookDao.getTitles(), prepareForTable(bookDao.getAllBooks()));
    }

    @Override
    public String getBookById(Long id) {
        return renderer.tableRender(bookDao.getTitles(), prepareForTable(List.of(bookDao.getBookById(id))));
    }

    @Override
    public String deleteBookById(Long id) {
        bookDao.deleteBookById(id);
        return String.format("Book with id: %s has delete", id);
    }

    @Override
    public String createNewBook(Long id, String bookName, Long authorId, Long genreId) {
        bookDao.insertBook(new Book(id, bookName, new Author(authorId, null), new Genre(genreId, null)));
        return String.format("Book:\n%s \nhas insert",
                renderer.tableRender(bookDao.getTitles(),
                        prepareForTable(List.of(bookDao.getBookById(id)))));
    }

    @Override
    public String updateBook(Long id, String bookName, Long authorId, Long genreId) {
        bookDao.insertBook(new Book(id, bookName, new Author(authorId, null), new Genre(genreId, null)));
        return String.format("Book:\n%s \nhas update", renderer.tableRender(bookDao.getTitles(),
                prepareForTable(List.of(bookDao.getBookById(id)))));
    }

    @Override
    public List<List<String>> prepareForTable(List<Book> books) {
        List<List<String>> tablePresentation = new ArrayList<>();
        for (Book b : books) {
            List<String> stringList = new ArrayList<>();
            stringList.add(b.getId().toString());
            stringList.add(b.getBookTitle());
            stringList.add(b.getAuthor().getFullName());
            stringList.add(b.getGenre().getGenreName());
            tablePresentation.add(stringList);
        }
        return tablePresentation;
    }
}

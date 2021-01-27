package ru.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Author;
import ru.library.models.Book;
import ru.library.models.Genre;
import ru.library.repositories.AuthorRepositories;
import ru.library.repositories.BookRepositories;
import ru.library.repositories.GenreRepositories;
import ru.library.utils.TableRenderer;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepositories bookDao;
    private final AuthorRepositories authorRepo;
    private final GenreRepositories genreRepo;
    private TableRenderer renderer;

    public BookServiceImpl(BookRepositories bookDao, TableRenderer renderer, AuthorRepositories authorRepo, GenreRepositories genreRepo) {
        this.bookDao = bookDao;
        this.renderer = renderer;
        this.authorRepo = authorRepo;
        this.genreRepo = genreRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public long getCount() {
        return bookDao.getBooksCount();
    }

    @Transactional(readOnly = true)
    @Override
    public String getAllBooks() {
        return renderer.tableRender(bookDao.getTitles(), prepareForTable(bookDao.getAllBooks()));
    }

    @Transactional(readOnly = true)
    @Override
    public String getBookById(long id) {
        return renderer.tableRender(bookDao.getTitles(), prepareForTable(List.of((bookDao.getBookById(id)).get())));
    }

    @Transactional
    @Override
    public String deleteBookById(long id) {
        bookDao.deleteBookById(id);
        return String.format("Book with id: %s has delete", id);
    }

    @Transactional
    @Override
    public String createNewBook(String bookName, long authorId, long genreId) {
        Author author = authorRepo.getAuthorById(authorId).get();
        Genre genre = genreRepo.getGenreById(genreId).get();
        bookDao.insertBook(new Book(0L, bookName, author, genre));
        return "Book has insert";
    }

    @Transactional
    @Override
    public String updateBook(long id, String bookName, long authorId, long genreId) {
        Book book = bookDao.getBookById(id).get();
        Author author = authorRepo.getAuthorById(authorId).get();
        Genre genre = genreRepo.getGenreById(genreId).get();
        book.setBookTitle(bookName);
        book.setGenre(genre);
        book.setAuthor(author);
        bookDao.insertBook(book);
        return String.format("Book:\n%s \nhas update", renderer.tableRender(bookDao.getTitles(),
                prepareForTable(List.of((bookDao.getBookById(id)).get()))));
    }

    @Override
    public List<List<String>> prepareForTable(List<Book> books) {
        List<List<String>> tablePresentation = new ArrayList<>();
        for (Book book : books) {
            List<String> columnList = new ArrayList<>();
            columnList.add(String.valueOf(book.getId()));
            columnList.add(book.getBookTitle());
            columnList.add(book.getAuthor().getFullName());
            columnList.add(book.getGenre().getGenreName());
            tablePresentation.add(columnList);
        }
        return tablePresentation;
    }
}

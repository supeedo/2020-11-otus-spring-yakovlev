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
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_WITH_ID_D_NOT_FOUND = "Book with id: %d, not found!";
    private static final String BOOK_WITH_ID_S_HAS_DELETE = "Book with id: %s has delete";
    private static final String BOOK_S_HAS_UPDATE = "Book:\n%s \nhas update";
    private static final String THERE_ARE_S_BOOKS_IN_THE_LIBRARY = "There are %s books in the library";
    private static final String BOOK_HAS_INSERT = "Book has insert";
    private final BookRepositories bookDao;
    private final AuthorRepositories authorRepo;
    private final GenreRepositories genreRepo;
    private final TableRenderer renderer;

    public BookServiceImpl(BookRepositories bookDao, TableRenderer renderer, AuthorRepositories authorRepo, GenreRepositories genreRepo) {
        this.bookDao = bookDao;
        this.renderer = renderer;
        this.authorRepo = authorRepo;
        this.genreRepo = genreRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public String getCount() {
        return String.format(THERE_ARE_S_BOOKS_IN_THE_LIBRARY, bookDao.getBooksCount());
    }

    @Transactional(readOnly = true)
    @Override
    public String getAllBooks() {
        return renderer.tableRender(bookDao.getTitles(), prepareForTable(bookDao.getAllBooks()));
    }

    @Transactional(readOnly = true)
    @Override
    public String getBookById(long bookId) {
        Optional<Book> book = bookDao.getBookById(bookId);
        if (book.isPresent()) {
            return renderer.tableRender(bookDao.getTitles(),
                    prepareForTable(List.of(book.get())));
        } else {
            return String.format(BOOK_WITH_ID_D_NOT_FOUND, bookId);
        }
    }

    @Transactional
    @Override
    public String deleteBookById(long bookId) {
        Optional<Book> book = bookDao.getBookById(bookId);
        if (book.isPresent()) {
            bookDao.deleteBook(book.get());
            return String.format(BOOK_WITH_ID_S_HAS_DELETE, bookId);
        } else {
            return String.format(BOOK_WITH_ID_D_NOT_FOUND, bookId);
        }
    }

    @Transactional
    @Override
    public String createNewBook(String bookName, long authorId, long genreId) {
        Author author = authorRepo.getAuthorById(authorId).get();
        Genre genre = genreRepo.getGenreById(genreId).get();
        bookDao.insertBook(new Book(0L, bookName, author, genre));
        return BOOK_HAS_INSERT;
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
        return String.format(BOOK_S_HAS_UPDATE, renderer.tableRender(bookDao.getTitles(),
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

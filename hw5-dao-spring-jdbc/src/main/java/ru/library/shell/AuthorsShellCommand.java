package ru.library.shell;

import org.springframework.shell.standard.ShellComponent;

@ShellComponent
public class AuthorsShellCommand {
//
//    private final BookDao bookDao;
//    private final TableRenderer renderer;
//
//    public AuthorsShellCommand(BookDaoJdbc bookDao, TableRenderer renderer) {
//        this.bookDao = bookDao;
//        this.renderer = renderer;
//    }
//
//    @ShellMethod(value = "Show books count in library", key = {"books count", "count books"})
//    public String showBooksCount() {
//        return String.format("There are %s books in the library", bookDao.getBooksCount());
//    }
//
//    @ShellMethod(value = "Show all books", key = {"show all books", "all books"})
//    public String showAllBooks() {
//        return renderer.renderer(bookDao.getTitles(), bookDao.getAllBooks());
//    }
//
//    @ShellMethod(value = "Get book by id", key = {"book id"})
//    public String showBookById(Long id) {
//        return renderer.renderer(bookDao.getTitles(), List.of(bookDao.getBookById(id)));
//    }
//
//    @ShellMethod(value = "Delete book by id", key = {"delete book"})
//    public String deleteBookById(Long id) {
//        bookDao.deleteBookById(id);
//        return String.format("Book with id: %s has delete", id);
//    }
//
//    @ShellMethod(value = "Insert book", key = {"insert book"})
//    public String insertNewBook(Long id, String bookName, Long authorId, Long genreId) {
//        bookDao.insertBook(new Book(id, bookName, new Author(authorId, null), new Genre(genreId, null)));
//        return String.format("Book:\n%s \nhas insert", renderer.renderer(bookDao.getTitles(), List.of(bookDao.getBookById(id))));
//    }
//
//    @ShellMethod(value = "Update book", key = {"update book"})
//    public String updateBook(Long id, String bookName, Long authorId, Long genreId) {
//        bookDao.updateBook(new Book(id, bookName, new Author(authorId, null), new Genre(genreId, null)));
//        return String.format("Book:\n%s \nhas update", renderer.renderer(bookDao.getTitles(), List.of(bookDao.getBookById(id))));
//    }


}

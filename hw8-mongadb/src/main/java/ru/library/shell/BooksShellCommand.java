package ru.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.library.service.BookServiceImpl;

@ShellComponent
public class BooksShellCommand {
    private final BookServiceImpl service;


    public BooksShellCommand(BookServiceImpl service) {
        this.service = service;
    }

    @ShellMethod(value = "Show books count in library", key = {"books count", "count books"})
    public String showBooksCount() {
        return service.getCount();
    }

    @ShellMethod(value = "Show all books", key = {"show all books", "all books"})
    public String showAllBooks() {
        return service.getAllBooks();
    }

    @ShellMethod(value = "Get book by id", key = {"book id"})
    public String showBookById(@ShellOption String id) {
        return service.getBookById(id);
    }

    @ShellMethod(value = "Delete book by id", key = {"delete book"})
    public String deleteBookById(@ShellOption String id) {
        return service.deleteBookById(id);
    }

    @ShellMethod(value = "Insert book", key = {"insert book", "create book", "book create"})
    public String insertNewBook(@ShellOption String bookName,
                                @ShellOption String authorId,
                                @ShellOption String genreId) {
        return service.createNewBook(bookName, authorId, genreId);
    }

    @ShellMethod(value = "Update book", key = {"update book"})
    public String updateBook(String id, String bookName, String authorId, String genreId) {
        return service.updateBook(id, bookName, authorId, genreId);
    }
}

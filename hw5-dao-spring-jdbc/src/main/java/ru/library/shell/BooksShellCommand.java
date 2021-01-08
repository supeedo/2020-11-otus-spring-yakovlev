package ru.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.library.service.BookServiceImpl;

@ShellComponent
public class BooksShellCommand {

    private final BookServiceImpl service;


    public BooksShellCommand(BookServiceImpl service) {
        this.service = service;
    }

    @ShellMethod(value = "Show books count in library", key = {"books count", "count books"})
    public String showBooksCount() {
        return String.format("There are %s books in the library", service.getCount());
    }

    @ShellMethod(value = "Show all books", key = {"show all books", "all books"})
    public String showAllBooks() {
        return service.getAllBooks();
    }

    @ShellMethod(value = "Get book by id", key = {"book id"})
    public String showBookById(Long id) {
        return service.getBookById(id);
    }

    @ShellMethod(value = "Delete book by id", key = {"delete book"})
    public String deleteBookById(Long id) {
        return service.deleteBookById(id);
    }

    @ShellMethod(value = "Insert book", key = {"insert book"})
    public String insertNewBook(Long id, String bookName, Long authorId, Long genreId) {
        return service.createNewBook(id, bookName, authorId, genreId);
    }

    @ShellMethod(value = "Update book", key = {"update book"})
    public String updateBook(Long id, String bookName, Long authorId, Long genreId) {
        return service.updateBook(id, bookName, authorId, genreId);
    }


}

package library.shell;

import library.service.BookServiceImpl;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

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
    public String showBookById(@ShellOption Long id) {
        return service.getBookById(id);
    }

    @ShellMethod(value = "Delete book by id", key = {"delete book"})
    public String deleteBookById(@ShellOption Long id) {
        return service.deleteBookById(id);
    }

    @ShellMethod(value = "Insert book", key = {"insert book", "create book", "book create"})
    public String insertNewBook(@ShellOption String bookName,
                                @ShellOption long authorId,
                                @ShellOption long genreId) {
        return service.createNewBook(bookName, authorId, genreId);
    }

    @ShellMethod(value = "Update book", key = {"update book"})
    public String updateBook(long id, String bookName, long authorId, long genreId) {
        return service.updateBook(id, bookName, authorId, genreId);
    }


}

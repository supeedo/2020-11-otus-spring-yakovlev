package library.shell;

import library.service.AuthorService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AuthorsShellCommand {

    private final AuthorService service;

    public AuthorsShellCommand(AuthorService service) {
        this.service = service;
    }

    @ShellMethod(value = "Show authors count in library", key = {"authors count", "count authors"})
    public String showAuthorCount() {
        return service.getCount();
    }

    @ShellMethod(value = "Show all authors", key = {"show all authors", "all authors"})
    public String showAllAuthors() {
        return service.getAllAuthors();
    }

    @ShellMethod(value = "Get author by id", key = {"author id"})
    public String showAuthorById(@ShellOption Long authorId) {
        return service.getAuthorById(authorId);
    }

    @ShellMethod(value = "Get author by name", key = {"author name"})
    public String showAuthorsByName(@ShellOption String authorName) {
        return service.getAuthorsByName(authorName);
    }

    @ShellMethod(value = "Delete author by id", key = {"delete author"})
    public String deleteAuthorById(@ShellOption Long authorId) {
        return service.deleteAuthorById(authorId);
    }

    @ShellMethod(value = "Insert author", key = {"create author", "author create"})
    public String insertNewAuthor(@ShellOption String authorFullName) {
        return service.createNewAuthor(authorFullName);
    }

    @ShellMethod(value = "Update author", key = {"update author"})
    public String updateAuthor(Long authorId, String authorFullName) {
        return service.updateAuthor(authorId, authorFullName);
    }

}

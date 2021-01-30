package ru.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.library.service.CommentService;

@ShellComponent
public class CommentShellCommand {
    private final CommentService service;

    public CommentShellCommand(CommentService service) {
        this.service = service;
    }

    @ShellMethod(value = "Show comments count in library", key = {"comments count", "count comments"})
    public String showCommentsCount() {
        return service.getCount();
    }

    @ShellMethod(value = "Show all comments", key = {"show all comments", "all comments"})
    public String showAllComments() {
        return service.getAllComments();
    }

    @ShellMethod(value = "Get comment by id", key = {"comment id"})
    public String showCommentById(@ShellOption long commentId) {
        return service.getCommentById(commentId);
    }

    @ShellMethod(value = "Get comment by Book id", key = {"all comm by book"})
    public String showAllCommentByBookId(@ShellOption long bookId) {
        return service.getAllCommentsByBookId(bookId);
    }

    @ShellMethod(value = "Delete comment by id", key = {"delete comment", "comment delete"})
    public String deleteCommentById(@ShellOption long commentId) {
        return service.deleteCommentById(commentId);
    }

    @ShellMethod(value = "Insert comment", key = {"insert comment", "create comment", "comments create"})
    public String insertNewComment(@ShellOption String commentText,
                                   @ShellOption long bookId) {
        return service.createNewComment(commentText, bookId);
    }

    @ShellMethod(value = "Update comment", key = {"update comment", "comment update"})
    public String updateComment(@ShellOption long commentId,
                                @ShellOption String commentText) {
        return service.updateComment(commentId, commentText);
    }
}

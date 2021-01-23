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
    public String showCommentById(@ShellOption Long commentId) {
        return service.getCommentById(commentId);
    }

    @ShellMethod(value = "Delete comment by id", key = {"delete comment", "comment delete"})
    public String deleteCommentById(@ShellOption Long commentId) {
        return service.deleteCommentById(commentId);
    }

    @ShellMethod(value = "Insert comment", key = {"insert comment"})
    public String insertNewComment(@ShellOption Long commentId,
                                   @ShellOption String commentText,
                                   @ShellOption Long bookId) {
        return service.createNewComment(commentId, commentText, bookId);
    }

    @ShellMethod(value = "Update comment", key = {"update comment", "comment update"})
    public String updateComment(Long commentId, String commentText, Long bookId) {
        return service.updateComment(commentId, commentText, bookId);
    }
}

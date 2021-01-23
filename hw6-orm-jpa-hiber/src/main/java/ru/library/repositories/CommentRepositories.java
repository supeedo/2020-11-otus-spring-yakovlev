package ru.library.repositories;

import ru.library.models.Author;
import ru.library.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositories {
    Long getCommentCount();

    Comment insertComment(Comment comment);

    void updateComment(Comment comment);

    void deleteCommentById(long commentId);

    Optional<Comment> getCommentById(long commentId);

    List<Comment> getAllComments();

    List<String> getTitles();
}

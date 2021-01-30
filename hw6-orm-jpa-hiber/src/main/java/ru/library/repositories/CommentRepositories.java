package ru.library.repositories;

import ru.library.models.BookComment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositories {
    long getCommentCount();

    BookComment insertComment(BookComment comment);

    void deleteComment(BookComment comment);

    Optional<BookComment> getCommentById(long commentId);

    List<BookComment> getAllComments();

    List<String> getTitles();
}

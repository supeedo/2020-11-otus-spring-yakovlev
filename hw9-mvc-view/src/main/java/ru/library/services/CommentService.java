package ru.library.services;

import ru.library.models.Comment;

import java.util.List;

public interface CommentService {
    String getCount();

    List<Comment> getAllComments();

    String getCommentById(Long commentId);

    String getAllCommentsByBookId(Long bookId);

    String deleteCommentById(Long commentId);

    String createNewComment(String comment, Long bookId);

    String updateComment(Long commentId, String commentText);

    Comment save (Comment comment);
}

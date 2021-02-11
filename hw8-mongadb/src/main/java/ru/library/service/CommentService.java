package ru.library.service;

import ru.library.models.Comment;

import java.util.List;

public interface CommentService {
    String getCount();

    String getAllComments();

    String getCommentById(String commentId);

    String getAllCommentsByBookId(String bookId);

    String deleteCommentById(String commentId);

    String createNewComment(String comment, String bookId);

    String updateComment(String commentId, String commentText);

    List<List<String>> prepareForTable(List<Comment> genres);
}

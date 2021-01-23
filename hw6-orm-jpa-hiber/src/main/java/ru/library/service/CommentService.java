package ru.library.service;

import ru.library.models.Comment;

import java.util.List;

public interface CommentService {

    String getCount();

    String getAllComments();

    String getCommentById(Long commentId);

    String deleteCommentById(Long commentId);

    String createNewComment(Long commentId, String comment, Long bookId);

    String updateComment(Long commentId, String comment, Long book);

    List<List<String>> prepareForTable(List<Comment> genres);
}

package ru.library.service;

import ru.library.models.BookComment;

import java.util.List;

public interface CommentService {

    String getCount();

    String getAllComments();

    String getCommentById(Long commentId);

    String deleteCommentById(Long commentId);

    String createNewComment(String comment, Long bookId);

    String updateComment(Long commentId, String commentText);

    List<List<String>> prepareForTable(List<BookComment> genres);
}

package library.service;


import library.models.BookComment;

import java.util.List;

public interface CommentService {

    String getCount();

    String getAllComments();

    String getCommentById(long commentId);

    String getAllCommentsByBookId(long bookId);

    String deleteCommentById(long commentId);

    String createNewComment(String comment, long bookId);

    String updateComment(long commentId, String commentText);

    List<List<String>> prepareForTable(List<BookComment> genres);
}

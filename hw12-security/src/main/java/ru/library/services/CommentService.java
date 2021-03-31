package ru.library.services;

import ru.library.dto.CommentDto;

import java.util.List;

public interface CommentService {
    long getCount();

    List<CommentDto> getAllComments();

    CommentDto getCommentById(Long commentId);

    List<CommentDto> getAllCommentsByBookId(Long bookId);

    void deleteCommentById(Long commentId);

    void createNewComment(CommentDto commentDto);

    void updateComment(CommentDto commentDto);

    void save (CommentDto commentDto);
}

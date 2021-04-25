package ru.library.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.dto.CommentDto;
import ru.library.dto.CommentMapper;
import ru.library.models.Comment;
import ru.library.repositories.CommentRepositories;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepositories commentRepositories;

    public CommentServiceImpl(CommentRepositories commentRepositories) {
        this.commentRepositories = commentRepositories;
    }

    @Transactional(readOnly = true)
    @Override
    public long getCount() {
        return commentRepositories.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepositories.findAll();
        return comments.stream().map(CommentMapper.INSTANCE::commentToCommentDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CommentDto getCommentById(Long commentId) {
        final Comment comment = commentRepositories.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        return CommentMapper.INSTANCE.commentToCommentDto(comment);
    }

    @Transactional
    @Override
    public List<CommentDto> getAllCommentsByBookId(Long bookId) {
        final List<Comment> comments = commentRepositories.getCommentsByBookId(bookId);
        return comments.stream().map(CommentMapper.INSTANCE::commentToCommentDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteCommentById(Long commentId) {
        commentRepositories.deleteById(commentId);
    }

    @Transactional
    @Override
    public void createNewComment(CommentDto commentDto) {
        Comment comment = CommentMapper.INSTANCE.commentDtoToComment(commentDto);
        commentRepositories.save(comment);
    }

    @Transactional
    @Override
    public void updateComment(CommentDto commentDto) {
        Comment comment = commentRepositories.findById(commentDto.getId()).orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.setComment(commentDto.getComment());
    }

    @Transactional
    @Override
    public void save(CommentDto commentDto) {
        Comment comment = CommentMapper.INSTANCE.commentDtoToComment(commentDto);
        commentRepositories.save(comment);
    }
}

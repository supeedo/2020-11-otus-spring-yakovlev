package ru.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Book;
import ru.library.models.Comment;
import ru.library.repositories.CommentRepositories;
import ru.library.utils.TableRenderer;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepositories commentDao;
    private final TableRenderer renderer;

    public CommentServiceImpl(CommentRepositories commentDao, TableRenderer renderer) {
        this.commentDao = commentDao;
        this.renderer = renderer;
    }

    @Transactional(readOnly = true)
    @Override
    public String getCount() {
        return String.format("There are %s comments in the library", commentDao.getCommentCount());
    }

    @Transactional(readOnly = true)
    @Override
    public String getAllComments() {
        return renderer.tableRender(commentDao.getTitles(),
                prepareForTable(commentDao.getAllComments()));
    }

    @Transactional(readOnly = true)
    @Override
    public String getCommentById(Long commentId) {
        return renderer.tableRender(commentDao.getTitles(),
                prepareForTable(List.of((commentDao.getCommentById(commentId)).get())));
    }

    @Transactional
    @Override
    public String deleteCommentById(Long commentId) {
        commentDao.deleteCommentById(commentId);
        return String.format("Comment with id: %s has delete", commentId);
    }

    @Transactional
    @Override
    public String createNewComment(Long commentId, String comment, Long bookId) {
        commentDao.insertComment(new Comment(commentId, comment,
                new Book(bookId, null, null, null, null)));
        return String.format("Comment:\n%s \nhas insert",
                renderer.tableRender(commentDao.getTitles(),
                        prepareForTable(List.of((commentDao.getCommentById(commentId)).get()))));
    }

    @Transactional
    @Override
    public String updateComment(Long commentId, String comment, Long bookId) {
        commentDao.insertComment(new Comment(commentId, comment,
                new Book(bookId, null, null, null, null)));
        return String.format("Comment:\n%s \nhas update", renderer.tableRender(commentDao.getTitles(),
                prepareForTable(List.of((commentDao.getCommentById(commentId)).get()))));
    }

    @Override
    public List<List<String>> prepareForTable(List<Comment> comments) {
        List<List<String>> tablePresentation = new ArrayList<>();
        for (Comment comment : comments) {
            List<String> columnList = new ArrayList<>();
            columnList.add(comment.getId().toString());
            columnList.add(comment.getComment());
            columnList.add(comment.getBook().getId().toString());
            tablePresentation.add(columnList);
        }
        return tablePresentation;
    }
}

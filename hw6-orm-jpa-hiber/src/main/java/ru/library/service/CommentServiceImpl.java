package ru.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Book;
import ru.library.models.BookComment;
import ru.library.repositories.BookRepositories;
import ru.library.repositories.CommentRepositories;
import ru.library.utils.TableRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepositories commentDao;
    private final BookRepositories bookRepo;
    private final TableRenderer renderer;

    public CommentServiceImpl(CommentRepositories commentDao, BookRepositories bookRepo, TableRenderer renderer) {
        this.commentDao = commentDao;
        this.bookRepo = bookRepo;
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
    public String getCommentById(long commentId) {
        Optional<BookComment> genre = commentDao.getCommentById(commentId);
        if (genre.isPresent()) {
            return renderer.tableRender(commentDao.getTitles(),
                    prepareForTable(List.of(genre.get())));
        } else {
            return String.format("Comment with id: %d, not found!", commentId);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public String getAllCommentsByBookId(long bookId) {
        List<BookComment> commentsList = commentDao.getAllCommentByBookId(bookId);
        if (!commentsList.isEmpty()) {
            return renderer.tableRender(commentDao.getTitles(),
                    prepareForTable(commentsList));
        } else {
            return String.format("Comments for the book with ID: %d, not found!", bookId);
        }
    }

    @Transactional
    @Override
    public String deleteCommentById(long commentId) {
        Optional<BookComment> genre = commentDao.getCommentById(commentId);
        if (genre.isPresent()) {
            commentDao.deleteComment(genre.get());
            return String.format("Comment with id: %s has delete", commentId);
        } else {
            return String.format("Comment with id: %d, not found!", commentId);
        }
    }

    @Transactional
    @Override
    public String createNewComment(String commentText, long bookId) {
        Book book = bookRepo.getBookById(bookId).get();
        BookComment comment = new BookComment();
        comment.setId(0L);
        comment.setComment(commentText);
        comment.setBook(book);
        commentDao.insertComment(comment);
        return "Comment has insert";
    }

    @Transactional
    @Override
    public String updateComment(long commentId, String commentText) {
        BookComment comment = commentDao.getCommentById(commentId).get();
        comment.setComment(commentText);
        commentDao.updateComment(comment);
        Optional<BookComment> updateComment = commentDao.getCommentById(commentId);
        if (updateComment.isPresent()) {
            return String.format("Comment:\n%s \nhas update", renderer.tableRender(commentDao.getTitles(),
                    prepareForTable(List.of(updateComment.get()))));
        } else {
            return String.format("BookComment with id: %d, not found!", commentId);
        }
    }

    @Override
    public List<List<String>> prepareForTable(List<BookComment> comments) {
        List<List<String>> tablePresentation = new ArrayList<>();
        for (BookComment comment : comments) {
            List<String> columnList = new ArrayList<>();
            columnList.add(String.valueOf(comment.getId()));
            columnList.add(comment.getComment());
            columnList.add(String.valueOf(comment.getBook().getId()));
            tablePresentation.add(columnList);
        }
        return tablePresentation;
    }
}

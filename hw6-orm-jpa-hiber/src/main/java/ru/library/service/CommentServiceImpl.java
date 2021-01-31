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
    private static final String COMMENT_WITH_ID_D_NOT_FOUND = "Comment with id: %d, not found!";
    private static final String THERE_ARE_S_COMMENTS_IN_THE_LIBRARY = "There are %s comments in the library";
    private static final String COMMENTS_FOR_THE_BOOK_WITH_ID_D_NOT_FOUND = "Comments for the book with ID: %d, not found!";
    private static final String COMMENT_WITH_ID_S_HAS_DELETE = "Comment with id: %s has delete";
    private static final String COMMENT_HAS_INSERT = "Comment has insert";
    private static final String COMMENT_S_HAS_UPDATE = "Comment:\n%s \nhas update";
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
        return String.format(THERE_ARE_S_COMMENTS_IN_THE_LIBRARY, commentDao.getCommentCount());
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
            return String.format(COMMENT_WITH_ID_D_NOT_FOUND, commentId);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public String getAllCommentsByBookId(long bookId) {
        Optional<Book> book = bookRepo.getBookById(bookId);
        if(book.isPresent()) {
            List<BookComment> commentsList = book.get().getComments();
            if (!commentsList.isEmpty()) {
                return renderer.tableRender(commentDao.getTitles(),
                        prepareForTable(commentsList));
            } else {
                return String.format(COMMENTS_FOR_THE_BOOK_WITH_ID_D_NOT_FOUND, bookId);
            }
        }else{
             return String.format(COMMENT_WITH_ID_D_NOT_FOUND, bookId);
        }
    }

    @Transactional
    @Override
    public String deleteCommentById(long commentId) {
        Optional<BookComment> genre = commentDao.getCommentById(commentId);
        if (genre.isPresent()) {
            commentDao.deleteComment(genre.get());
            return String.format(COMMENT_WITH_ID_S_HAS_DELETE, commentId);
        } else {
            return String.format(COMMENT_WITH_ID_D_NOT_FOUND, commentId);
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
        return COMMENT_HAS_INSERT;
    }

    @Transactional
    @Override
    public String updateComment(long commentId, String commentText) {
        BookComment comment = commentDao.getCommentById(commentId).get();
        comment.setComment(commentText);
        Optional<BookComment> updateComment = commentDao.getCommentById(commentId);
        if (updateComment.isPresent()) {
            return String.format(COMMENT_S_HAS_UPDATE, renderer.tableRender(commentDao.getTitles(),
                    prepareForTable(List.of(updateComment.get()))));
        } else {
            return String.format(COMMENT_WITH_ID_D_NOT_FOUND, commentId);
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

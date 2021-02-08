package ru.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Book;
import ru.library.models.Comment;
import ru.library.repository.BookRepositories;
import ru.library.repository.CommentRepositories;
import ru.library.utils.TableRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private static final String COMMENT_WITH_ID_D_NOT_FOUND = "Comment with id: %s, not found!";
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
        return String.format(THERE_ARE_S_COMMENTS_IN_THE_LIBRARY, commentDao.count());
    }

    @Transactional(readOnly = true)
    @Override
    public String getAllComments() {
        return renderer.tableRender(getTitles(),
                prepareForTable(commentDao.findAll()));
    }

    @Transactional(readOnly = true)
    @Override
    public String getCommentById(String commentId) {
        Optional<Comment> genre = commentDao.findById(commentId);
        if (genre.isPresent()) {
            return renderer.tableRender(getTitles(),
                    prepareForTable(List.of(genre.get())));
        } else {
            return String.format(COMMENT_WITH_ID_D_NOT_FOUND, commentId);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public String getAllCommentsByBookId(String bookId) {
        Optional<Book> book = bookRepo.findById(bookId);
        if(book.isPresent()) {
            List<Comment> commentsList = book.get().getComments();
            if (!commentsList.isEmpty()) {
                return renderer.tableRender(getTitles(),
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
    public String deleteCommentById(String commentId) {
        Optional<Comment> genre = commentDao.findById(commentId);
        if (genre.isPresent()) {
            commentDao.delete(genre.get());
            return String.format(COMMENT_WITH_ID_S_HAS_DELETE, commentId);
        } else {
            return String.format(COMMENT_WITH_ID_D_NOT_FOUND, commentId);
        }
    }

    @Transactional
    @Override
    public String createNewComment(String commentText, String bookId) {
        Book book = bookRepo.findById(bookId).get();
        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setBook(book);
        commentDao.save(comment);
        return COMMENT_HAS_INSERT;
    }

    @Transactional
    @Override
    public String updateComment(String commentId, String commentText) {
        Comment comment = commentDao.findById(commentId).get();
        comment.setComment(commentText);
        Optional<Comment> updateComment = commentDao.findById(commentId);
        if (updateComment.isPresent()) {
            return String.format(COMMENT_S_HAS_UPDATE, renderer.tableRender(getTitles(),
                    prepareForTable(List.of(updateComment.get()))));
        } else {
            return String.format(COMMENT_WITH_ID_D_NOT_FOUND, commentId);
        }
    }

    @Override
    public List<List<String>> prepareForTable(List<Comment> comments) {
        List<List<String>> tablePresentation = new ArrayList<>();
        for (Comment comment : comments) {
            List<String> columnList = new ArrayList<>();
            columnList.add(String.valueOf(comment.getId()));
            columnList.add(comment.getComment());
            columnList.add(String.valueOf(comment.getBook().getId()));
            tablePresentation.add(columnList);
        }
        return tablePresentation;
    }

    public List<String> getTitles() {
        return List.of("id", "Comment", "Book");
    }
}

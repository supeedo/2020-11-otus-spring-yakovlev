package ru.library.repositories;

import org.springframework.stereotype.Repository;
import ru.library.models.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoriesJpa implements CommentRepositories {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long getCommentCount() {
        Query query = em.createQuery("SELECT COUNT (c) FROM BookComment c");
        return (long) query.getSingleResult();
    }

    @Override
    public BookComment insertComment(BookComment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public void updateComment(BookComment comment) {
        Query query = em.createQuery("UPDATE BookComment c SET c.comment = :comment, c.book.id = :book WHERE c.id = :id");
        query.setParameter("id", comment.getId());
        query.setParameter("comment", comment.getComment());
        query.setParameter("book", comment.getBook().getId());
        query.executeUpdate();
    }

    @Override
    public void deleteCommentById(long commentId) {
        Query query = em.createQuery("DELETE FROM BookComment c WHERE c.id = :commentId");
        query.setParameter("commentId", commentId);
        query.executeUpdate();
    }

    @Override
    public Optional<BookComment> getCommentById(long commentId) {
        return Optional.ofNullable(em.find(BookComment.class, commentId));
    }

    @Override
    public List<BookComment> getAllCommentByBookId(long bookId) {
        TypedQuery<BookComment> query = em.createQuery("SELECT c FROM BookComment c where c.book.id = :bookId", BookComment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    public List<BookComment> getAllComments() {
        TypedQuery<BookComment> query = em.createQuery("SELECT c FROM BookComment c", BookComment.class);
        return query.getResultList();
    }

    @Override
    public List<String> getTitles() {
        return List.of("id", "Comment", "Book");
    }
}

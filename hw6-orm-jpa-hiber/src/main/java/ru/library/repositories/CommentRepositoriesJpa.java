package ru.library.repositories;

import org.springframework.stereotype.Component;
import ru.library.models.BookComment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
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
    public void deleteComment(BookComment comment) {
        em.remove(comment);
    }

    @Override
    public Optional<BookComment> getCommentById(long commentId) {
        return Optional.ofNullable(em.find(BookComment.class, commentId));
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

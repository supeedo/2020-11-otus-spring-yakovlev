package ru.library.repositories;

import org.springframework.stereotype.Repository;
import ru.library.models.Comment;

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
    public Long getCommentCount() {
        Query query = em.createQuery("SELECT COUNT (c) FROM Comment c");
        return (Long) query.getSingleResult();
    }

    @Override
    public Comment insertComment(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public void updateComment(Comment comment) {
        Query query = em.createQuery("UPDATE Comment c SET c.comment = :comment, c.book = :book WHERE c.id = :id");
        query.setParameter("id", comment.getId());
        query.setParameter("comment", comment.getComment());
        query.setParameter("book", comment.getBook());
    }

    @Override
    public void deleteCommentById(long commentId) {
        Query query = em.createQuery("DELETE FROM Comment c WHERE c.id = :commentId");
        query.setParameter("commentId", commentId);
        query.executeUpdate();
    }

    @Override
    public Optional<Comment> getCommentById(long commentId) {
        return Optional.ofNullable(em.find(Comment.class, commentId));
    }

    @Override
    public List<Comment> getAllComments() {
        TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public List<String> getTitles() {
        return List.of("id", "Comment", "Book");
    }
}

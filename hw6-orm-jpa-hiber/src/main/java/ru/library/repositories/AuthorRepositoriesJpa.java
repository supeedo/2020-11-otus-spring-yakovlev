package ru.library.repositories;

import org.springframework.stereotype.Repository;
import ru.library.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoriesJpa implements AuthorRepositories {

    @PersistenceContext
    private EntityManager em;

    public long getAuthorsCount() {
        Query query = em.createQuery("SELECT COUNT(a) FROM Author a");
        return (long) query.getSingleResult();
    }

    @Override
    public Author insertAuthor(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public void updateAuthorById(Author author) {
        Query query = em.createQuery("UPDATE Author a SET a.fullName = :fullName WHERE a.id = :id");
        query.setParameter("fullName", author.getFullName());
        query.setParameter("id", author.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteAuthorById(long authorId) {
        Query query = em.createQuery("delete from Author a where a.id = :authorId");
        query.setParameter("authorId", authorId);
        query.executeUpdate();
    }

    @Override
    public Optional<Author> getAuthorById(long authorId) {
        return Optional.ofNullable(em.find(Author.class, authorId));

    }

    @Override
    public List<Author> getAllAuthors() {
        TypedQuery<Author> query = em.createQuery("select s from Author s", Author.class);
        return query.getResultList();
    }

    @Override
    public List<String> getTitles() {
        return List.of("id", "Author full name");
    }

}

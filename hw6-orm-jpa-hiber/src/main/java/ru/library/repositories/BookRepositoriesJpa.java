package ru.library.repositories;

import org.springframework.stereotype.Repository;
import ru.library.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoriesJpa implements BookRepositories {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long getBooksCount() {
        Query query = em.createQuery("SELECT COUNT(b) FROM Book b");
        return (long) query.getSingleResult();
    }

    @Override
    public Book insertBook(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public void updateBook(Book book) {
        Query query = em.createQuery("UPDATE Book b SET b.bookTitle = :bookTitle, b.author.id=:authorId, b.genre.id=:genreId WHERE b.id=:id");
        query.setParameter("id", book.getId());
        query.setParameter("genreId", book.getGenre().getId());
        query.setParameter("authorId", book.getAuthor().getId());
        query.setParameter("bookTitle", book.getBookTitle());
        query.executeUpdate();
    }

    @Override
    public void deleteBook(Book book) {
        em.remove(book);
    }

    @Override
    public Optional<Book> getBookById(long bookId) {
        return Optional.ofNullable(em.find(Book.class, bookId));
    }

    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public List<String> getTitles() {
        return List.of("id", "Book name", "Author full name", "Genre");
    }

}

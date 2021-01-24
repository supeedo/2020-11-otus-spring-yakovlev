package ru.library.repositories;

import org.springframework.stereotype.Repository;
import ru.library.models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoriesJpa implements GenreRepositories {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long getGenreCount() {
        Query query = em.createQuery("SELECT COUNT(g) FROM Genre g");
        return (long) query.getSingleResult();
    }

    @Override
    public Genre insertGenre(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public void updateGenre(Genre genre) {
        Query query = em.createQuery("UPDATE Genre g SET g.genreName = :genreName WHERE g.id = :id");
        query.setParameter("genreName", genre.getGenreName());
        query.setParameter("id", genre.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteGenreById(long genreId) {
        Query query = em.createQuery("delete from Genre g where g.id = :genreId");
        query.setParameter("genreId", genreId);
        query.executeUpdate();
    }

    @Override
    public Optional<Genre> getGenreById(long genreId) {
        return Optional.ofNullable(em.find(Genre.class, genreId));
    }

    @Override
    public List<Genre> getAllGenres() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public List<String> getTitles() {
        return List.of("id", "Genre name");
    }

}

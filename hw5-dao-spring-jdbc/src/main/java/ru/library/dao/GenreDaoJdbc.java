package ru.library.dao;

import org.springframework.stereotype.Repository;
import ru.library.dto.Genre;

import java.util.List;

@Repository
public class GenreDaoJdbc implements GenreDao {
    @Override
    public int genreCount() {
        return 0;
    }

    @Override
    public void insert(Genre genre) {

    }

    @Override
    public void update(Genre genre) {

    }

    @Override
    public void deleteByGenreId(long genreId) {

    }

    @Override
    public Genre getByGenreId(long genreId) {
        return null;
    }

    @Override
    public List<Genre> getAllGenres() {
        return null;
    }
}

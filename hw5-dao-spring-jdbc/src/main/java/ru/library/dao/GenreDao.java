package ru.library.dao;

import ru.library.dto.Author;
import ru.library.dto.Genre;

import java.util.List;

public interface GenreDao {
    int genreCount();
    void insert(Genre genre);
    void update(Genre genre);
    void deleteByGenreId(long genreId);
    Genre getByGenreId(long genreId);
    List<Genre> getAllGenres();
}

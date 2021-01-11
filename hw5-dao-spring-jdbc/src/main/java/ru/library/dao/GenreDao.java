package ru.library.dao;

import ru.library.dto.Genre;

import java.util.List;

public interface GenreDao {
    int getGenreCount();

    void insertGenre(Genre genre);

    void updateGenre(Genre genre);

    void deleteGenreById(long genreId);

    Genre getGenreById(long genreId);

    List<Genre> getAllGenres();

    List<String> getTitles();
}

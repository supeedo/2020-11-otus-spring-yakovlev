package ru.library.repositories;

import ru.library.models.Genre;

import java.util.List;

public interface GenreRepositories {
    int getGenreCount();

    void insertGenre(Genre genre);

    void updateGenre(Genre genre);

    void deleteGenreById(long genreId);

    Genre getGenreById(long genreId);

    List<Genre> getAllGenres();

    List<String> getTitles();
}

package ru.library.repositories;

import ru.library.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepositories {
    long getGenreCount();

    Genre insertGenre(Genre genre);

    void updateGenre(Genre genre);

    void deleteGenre(Genre genre);

    Optional<Genre> getGenreById(long genreId);

    List<Genre> getAllGenres();

    List<String> getTitles();
}

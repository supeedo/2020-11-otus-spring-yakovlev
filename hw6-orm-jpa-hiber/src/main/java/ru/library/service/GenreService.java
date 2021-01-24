package ru.library.service;

import ru.library.models.Genre;

import java.util.List;

public interface GenreService {
    String getCount();

    String getAllGenre();

    String getGenreById(long genreId);

    String deleteGenreById(long genreId);

    String createNewGenre(String genreName);

    String updateGenre(long genreId, String genreName);

    List<List<String>> prepareForTable(List<Genre> genres);
}

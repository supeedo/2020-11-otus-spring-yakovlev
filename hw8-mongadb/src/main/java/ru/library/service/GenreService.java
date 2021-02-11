package ru.library.service;

import ru.library.models.Genre;

import java.util.List;

public interface GenreService {
    String getCount();

    String getAllGenre();

    String getGenreById(String genreId);

    String deleteGenreById(String genreId);

    String createNewGenre(String genreName);

    String updateGenre(String genreId, String genreName);

    List<List<String>> prepareForTable(List<Genre> genres);
}

package ru.library.service;

import ru.library.dto.Genre;

import java.util.List;

public interface GenreService {
    String getCount();

    String getAllGenre();

    String getGenreById(Long genreId);

    String deleteGenreById(Long genreId);

    String createNewGenre(Long genreId, String genreName);

    String updateGenre(Long genreId, String genreName);

    List<List<String>> prepareForTable(List<Genre> genres);
}

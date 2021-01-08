package ru.library.service;

import ru.library.dto.Genre;

import java.util.List;

public interface GenreService {
    String getCount();

    String getAllGenre();

    String getGenreById(Long genreId);

    String deleteGenreById(Long genreId);

    String createNewGenre(Long id, String genreName);

    String updateGenre(Long id, String genreName);

    List<List<String>> prepareForTable(List<Genre> genres);
}

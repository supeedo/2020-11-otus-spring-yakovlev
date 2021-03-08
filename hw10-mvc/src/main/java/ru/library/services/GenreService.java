package ru.library.services;

import ru.library.Dto.GenreDto;
import ru.library.models.Genre;

import java.util.List;

public interface GenreService {

    String getCount();

    List<GenreDto> getAllGenre();

    Genre getGenreById(Long genreId);

    String deleteGenreById(Long genreId);

    String createNewGenre(String genreName);

    String updateGenre(Long genreId, String genreName);

    Genre save (Genre genre);
}

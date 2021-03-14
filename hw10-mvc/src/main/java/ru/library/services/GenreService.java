package ru.library.services;

import ru.library.Dto.GenreDto;
import ru.library.models.Genre;

import java.util.List;

public interface GenreService {

    long getCount();

    List<GenreDto> getAllGenre();

    GenreDto getGenreById(Long genreId);

    void deleteGenreById(Long genreId);

    void createNewGenre(GenreDto genreDto);

    void updateGenre(GenreDto genreDto);

    void save (GenreDto genreDto);
}

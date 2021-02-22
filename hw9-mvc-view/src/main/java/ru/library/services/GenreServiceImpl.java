package ru.library.services;

import org.springframework.stereotype.Service;
import ru.library.models.Genre;
import ru.library.repositories.GenreRepositories;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepositories genreRepositories;

    public GenreServiceImpl(GenreRepositories genreRepositories) {
        this.genreRepositories = genreRepositories;
    }

    @Override
    public String getCount() {
        return null;
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreRepositories.findAll();
    }

    @Override
    public Genre getGenreById(Long genreId) {
        return genreRepositories.findById(genreId).orElseThrow(() -> new IllegalArgumentException("Genre not found"));
    }

    @Override
    public String deleteGenreById(Long genreId) {
        return null;
    }

    @Override
    public String createNewGenre(String genreName) {
        return null;
    }

    @Override
    public String updateGenre(Long genreId, String genreName) {
        return null;
    }

    @Override
    public Genre save(Genre genre) {
        return null;
    }
}

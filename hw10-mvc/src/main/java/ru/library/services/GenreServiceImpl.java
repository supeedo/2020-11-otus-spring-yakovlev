package ru.library.services;

import org.springframework.stereotype.Service;
import ru.library.Dto.BookMapper;
import ru.library.Dto.GenreDto;
import ru.library.Dto.GenreMapper;
import ru.library.models.Genre;
import ru.library.repositories.GenreRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<GenreDto> getAllGenre() {
        List<Genre> genres = genreRepositories.findAll();
        System.out.println("Из Базы данных - " + genres.toString());
//        List<GenreDto> dtos = new ArrayList<>();
//        for (Genre genre : genres) {
//            dtos.add(GenreMapper.INSTANCE.genreToGenreDto(genre));
//        }
        List<GenreDto> dtos = genres.stream().map(GenreMapper.INSTANCE::genreToGenreDto).collect(Collectors.toList());
        System.out.println("Service dtos - " + dtos);
        return dtos;
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

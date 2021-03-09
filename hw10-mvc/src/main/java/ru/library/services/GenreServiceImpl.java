package ru.library.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.Dto.GenreDto;
import ru.library.Dto.GenreMapper;
import ru.library.models.Genre;
import ru.library.repositories.GenreRepositories;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepositories genreRepositories;

    public GenreServiceImpl(GenreRepositories genreRepositories) {
        this.genreRepositories = genreRepositories;
    }

    @Transactional(readOnly = true)
    @Override
    public long getCount() {
        return genreRepositories.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<GenreDto> getAllGenre() {
        final List<Genre> genres = genreRepositories.findAll();
        return genres.stream().map(GenreMapper.INSTANCE::genreToGenreDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public GenreDto getGenreById(Long genreId) {
        Genre genre = genreRepositories.findById(genreId).orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        return GenreMapper.INSTANCE.genreToGenreDto(genre);
    }

    @Transactional
    @Override
    public void deleteGenreById(Long genreId) {
        genreRepositories.deleteById(genreId);
    }

    @Transactional
    @Override
    public void createNewGenre(GenreDto genreDto) {
        Genre genre = GenreMapper.INSTANCE.genreDtoToGenre(genreDto);
        genreRepositories.save(genre);
    }

    @Transactional
    @Override
    public void updateGenre(GenreDto genreDto) {
        Genre genre = genreRepositories.findById(genreDto.getId()).orElseThrow(() -> new IllegalArgumentException("Genre not found"));
        genre.setGenreName(genreDto.getGenreName());
    }

    @Transactional
    @Override
    public void save(GenreDto genreDto) {
        Genre genre = GenreMapper.INSTANCE.genreDtoToGenre(genreDto);
        genreRepositories.save(genre);
    }
}

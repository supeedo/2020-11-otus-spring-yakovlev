package ru.library.service;

import ru.library.models.Genre;
import ru.library.repositories.GenreRepositories;
import ru.library.utils.TableRenderer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private static final String THERE_ARE_S_GENRES_IN_THE_LIBRARY = "There are %s genres in the library";
    private static final String GENRE_WITH_ID_D_NOT_FOUND = "Genre with id: %d, not found!";
    private static final String GENRE_WITH_ID_S_HAS_DELETE = "Genre with id: %s has delete";
    private static final String GENRE_S_HAS_UPDATE = "Genre:\n%s \nhas update";
    private static final String GENRE_HAS_INSERT = "Genre has insert";
    private final GenreRepositories genreDao;
    private final TableRenderer renderer;

    public GenreServiceImpl(GenreRepositories genreDao, TableRenderer renderer) {
        this.genreDao = genreDao;
        this.renderer = renderer;
    }

    @Transactional(readOnly = true)
    @Override
    public String getCount() {
        return String.format(THERE_ARE_S_GENRES_IN_THE_LIBRARY, genreDao.count());
    }

    @Transactional(readOnly = true)
    @Override
    public String getAllGenre() {
        return renderer.tableRender(getTitles(), prepareForTable(genreDao.findAll()));
    }

    @Transactional(readOnly = true)
    @Override
    public String getGenreById(long genreId) {
        Optional<Genre> genre = genreDao.findById(genreId);
        if (genre.isPresent()) {
            return renderer.tableRender(getTitles(),
                    prepareForTable(List.of(genre.get())));
        } else {
            return String.format(GENRE_WITH_ID_D_NOT_FOUND, genreId);
        }
    }

    @Transactional
    @Override
    public String deleteGenreById(long genreId) {
        Optional<Genre> genre = genreDao.findById(genreId);
        if (genre.isPresent()) {
            genreDao.delete(genre.get());
            return String.format(GENRE_WITH_ID_S_HAS_DELETE, genreId);
        } else {
            return String.format(GENRE_WITH_ID_D_NOT_FOUND, genreId);
        }
    }

    @Transactional
    @Override
    public String createNewGenre(String genreName) {
        genreDao.save(new Genre(0L, genreName));
        return GENRE_HAS_INSERT;
    }

    @Transactional
    @Override
    public String updateGenre(long genreId, String genreName) {
        Genre genre = genreDao.findById(genreId).get();
        genre.setGenreName(genreName);
        Optional<Genre> updateGenre = genreDao.findById(genreId);
        if (updateGenre.isPresent()) {
            return String.format(GENRE_S_HAS_UPDATE, renderer.tableRender(getTitles(),
                    prepareForTable(List.of(updateGenre.get()))));
        } else {
            return String.format(GENRE_WITH_ID_D_NOT_FOUND, genreId);
        }
    }

    @Override
    public List<List<String>> prepareForTable(List<Genre> genres) {
        List<List<String>> tablePresentation = new ArrayList<>();
        for (Genre genre : genres) {
            List<String> columnList = new ArrayList<>();
            columnList.add(String.valueOf(genre.getId()));
            columnList.add(genre.getGenreName());
            tablePresentation.add(columnList);
        }
        return tablePresentation;
    }

    public List<String> getTitles() {
        return List.of("id", "Genre name");
    }
}

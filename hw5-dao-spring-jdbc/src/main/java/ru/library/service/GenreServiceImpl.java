package ru.library.service;

import org.springframework.stereotype.Service;
import ru.library.dao.GenreDao;
import ru.library.dao.GenreDaoJdbc;
import ru.library.dto.Genre;
import ru.library.utils.TableRenderer;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final TableRenderer renderer;

    public GenreServiceImpl(GenreDaoJdbc genreDao, TableRenderer renderer) {
        this.genreDao = genreDao;
        this.renderer = renderer;
    }

    @Override
    public String getCount() {
        return String.format("There are %s genres in the library", genreDao.getGenreCount());
    }

    @Override
    public String getAllGenre() {
        return renderer.tableRender(genreDao.getTitles(), prepareForTable(genreDao.getAllGenres()));
    }

    @Override
    public String getGenreById(Long genreId) {
        return renderer.tableRender(genreDao.getTitles(),
                prepareForTable(List.of(genreDao.getGenreById(genreId))));
    }

    @Override
    public String deleteGenreById(Long genreId) {
        genreDao.deleteGenreById(genreId);
        return String.format("Genre with id: %s has delete", genreId);
    }

    @Override
    public String createNewGenre(Long genreId, String genreName) {
        genreDao.insertGenre(new Genre(genreId, genreName));
        return String.format("Genre:\n%s \nhas insert", renderer.tableRender(genreDao.getTitles(),
                prepareForTable(List.of(genreDao.getGenreById(genreId)))));
    }

    @Override
    public String updateGenre(Long genreId, String genreName) {
        genreDao.updateGenre(new Genre(genreId, genreName));
        return String.format("Genre:\n%s \nhas update", renderer.tableRender(genreDao.getTitles(),
                prepareForTable(List.of(genreDao.getGenreById(genreId)))));
    }

    @Override
    public List<List<String>> prepareForTable(List<Genre> genres) {
        List<List<String>> tablePresentation = new ArrayList<>();
        for (Genre genre : genres) {
            List<String> columnList = new ArrayList<>();
            columnList.add(genre.getId().toString());
            columnList.add(genre.getGenreName());
            tablePresentation.add(columnList);
        }
        return tablePresentation;
    }
}

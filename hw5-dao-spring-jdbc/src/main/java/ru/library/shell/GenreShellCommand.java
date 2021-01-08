package ru.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.library.dao.GenreDao;
import ru.library.dto.Genre;
import ru.library.utils.TableRenderer;

@ShellComponent
public class GenreShellCommand {

    private final GenreDao genreDao;
    private final TableRenderer renderer;

    public GenreShellCommand(GenreDao genreDao, TableRenderer renderer) {
        this.genreDao = genreDao;
        this.renderer = renderer;
    }

    @ShellMethod(value = "Show genres count in library", key = {"genres count", "count genres"})
    public String showGenresCount() {
        return String.format("There are %s genres in the library", genreDao.getGenreCount());
    }

    @ShellMethod(value = "Show all genres", key = {"show all genres", "all genres"})
    public String showAllGenres() {
        return null
//                renderer.renderer(genreDao.getTitles(), genreDao.getAllBooks())
                ;
    }

    @ShellMethod(value = "Get genre by id", key = {"genre id"})
    public String showGenreById(Long id) {
        return null
//                renderer.renderer(genreDao.getTitles(), List.of(genreDao.getBookById(id)))
                ;
    }

    @ShellMethod(value = "Delete genre by id", key = {"delete genre"})
    public String deleteGenreById(Long id) {
        genreDao.deleteGenreById(id);
        return String.format("Genre with id: %s has delete", id);
    }

    @ShellMethod(value = "Insert genre", key = {"insert genre"})
    public String insertNewGenre(Long id, String genreName) {
        genreDao.insertGenre(new Genre(id, genreName));
        return null;
//                String.format("Genre:\n%s \nhas insert", renderer.renderer(genreDao.getTitles(), List.of(genreDao.getGenreById(id))));
    }

    @ShellMethod(value = "Update genre", key = {"update genre"})
    public String updateGenre(Long id, String genreName) {
        genreDao.updateGenre(new Genre(id, genreName));
        return null;
//                String.format("Genre:\n%s \nhas update", renderer.renderer(genreDao.getTitles(), List.of(genreDao.getBookById(id))));
    }


}

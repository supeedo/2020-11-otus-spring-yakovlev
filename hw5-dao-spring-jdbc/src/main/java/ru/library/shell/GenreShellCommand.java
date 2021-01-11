package ru.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.library.service.GenreService;

@ShellComponent
public class GenreShellCommand {

    private final GenreService service;

    public GenreShellCommand(GenreService service) {
        this.service = service;
    }

    @ShellMethod(value = "Show genres count in library", key = {"genres count", "count genres"})
    public String showGenresCount() {
        return service.getCount();
    }

    @ShellMethod(value = "Show all genres", key = {"show all genres", "all genres"})
    public String showAllGenres() {
        return service.getAllGenre();
    }

    @ShellMethod(value = "Get genre by id", key = {"genre id"})
    public String showGenreById(Long genreId) {
        return service.getGenreById(genreId);
    }

    @ShellMethod(value = "Delete genre by id", key = {"delete genre", "genre delete"})
    public String deleteGenreById(Long genreId) {
        return service.deleteGenreById(genreId);
    }

    @ShellMethod(value = "Insert genre", key = {"insert genre"})
    public String insertNewGenre(Long genreId, String genreName) {
        return service.createNewGenre(genreId, genreName);
    }

    @ShellMethod(value = "Update genre", key = {"update genre", "genre update"})
    public String updateGenre(Long genreId, String genreName) {
        return service.updateGenre(genreId, genreName);
    }

}

package ru.library.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.library.dto.GenreDto;
import ru.library.services.GenreService;

import java.util.List;

@Controller
public class GenreController {

    private static final Logger log = LoggerFactory.getLogger(CommentRestController.class);

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public String showAuthors(Model model){
        final List<GenreDto> genres = genreService.getAllGenre();
        log.debug("Genres list from db: {}", genres);
        model.addAttribute("genres", genres);
        return "genre-list";
    }
}

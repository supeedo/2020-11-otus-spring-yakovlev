package ru.library.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.library.dto.AuthorDto;
import ru.library.services.AuthorService;

import java.util.List;

@Controller
public class AuthorsController {

    private static final Logger log = LoggerFactory.getLogger(AuthorsController.class);

    private final AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String showAuthors(Model model){
        final List<AuthorDto> authors = authorService.getAllAuthors();
        log.debug("Authors list from db: {}", authors);
        model.addAttribute("authors", authors);
        return "author-list";
    }
}

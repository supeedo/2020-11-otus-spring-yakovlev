package ru.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.library.Dto.AuthorDto;
import ru.library.Dto.BookDto;
import ru.library.Dto.GenreDto;
import ru.library.services.AuthorService;
import ru.library.services.BookServiceImpl;
import ru.library.services.GenreService;

import java.util.List;

@Controller
public class BookController {

    BookServiceImpl bookService;
    GenreService genreService;
    AuthorService authorService;

    @Autowired
    public BookController(BookServiceImpl bookService,
                          GenreService genreService,
                          AuthorService authorService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @GetMapping("/edit-book")
    public String updateBook(@RequestParam("id") Long id,
                             Model model) {
        BookDto book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/edit-book")
    public String saveBook(BookDto book) {
        bookService.save(book);
        return "redirect:/";
    }

    @GetMapping("/add-book")
    public String saveBook(Model model) {
        List<GenreDto> genres = genreService.getAllGenre();
        System.out.println("Полученные ДТО - " + genres.toString());
        List<AuthorDto> authors = authorService.getAllAuthors();
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "add-book";
    }
}

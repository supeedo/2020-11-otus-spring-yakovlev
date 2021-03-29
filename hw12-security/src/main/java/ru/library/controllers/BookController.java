package ru.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.library.dto.AuthorDto;
import ru.library.dto.BookDto;
import ru.library.dto.CommentDto;
import ru.library.dto.GenreDto;
import ru.library.services.AuthorService;
import ru.library.services.BookServiceImpl;
import ru.library.services.CommentServiceImpl;
import ru.library.services.GenreService;

import java.util.List;

@Controller
public class BookController {

    private final BookServiceImpl bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final CommentServiceImpl commentService;

    @Autowired
    public BookController(BookServiceImpl bookService,
                          GenreService genreService,
                          AuthorService authorService,
                          CommentServiceImpl commentService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
        this.commentService = commentService;
    }

    @GetMapping("/edit-book")
    public String updateBook(@RequestParam("id") Long id,
                             Model model) {
        final BookDto book = bookService.getBookById(id);
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
        final List<GenreDto> genres = genreService.getAllGenre();
        final List<AuthorDto> authors = authorService.getAllAuthors();
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "add-book";
    }

    @GetMapping("/comment")
    public String showBook(@RequestParam("id") Long id,
                           Model model) {
        final BookDto book = bookService.getBookById(id);
        final List<CommentDto> comments = commentService.getAllCommentsByBookId(id);
        model.addAttribute("book", book);
        model.addAttribute("comments", comments);
        return "book";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") Long id,
                             Model model) {
        bookService.deleteBookById(id);
        return "redirect:/";
    }
}

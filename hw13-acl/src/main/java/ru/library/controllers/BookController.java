package ru.library.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

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

    @GetMapping("/edit-book/{id}")
    public String updateBook(@PathVariable Long id,
                             Model model) {
        log.debug("ID of the book to be updated: {}", id);
        final BookDto book = bookService.getBookById(id);
        log.debug("Book from db: {}", book);
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/edit-book")
    public String saveBook(BookDto book) {
        log.debug("book to save to db: {}", book);
        bookService.save(book);
        return "redirect:/";
    }

    @GetMapping("/add-book")
    public String saveBook(Model model) {
        final List<GenreDto> genres = genreService.getAllGenre();
        log.debug("Genres list from base: {}", genres);
        final List<AuthorDto> authors = authorService.getAllAuthors();
        log.debug("Authors list from base: {}", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "add-book";
    }

    @GetMapping("/comment/{id}")
    public String showBook(@PathVariable Long id,
                           Model model) {
        log.debug("Book id: {}", id);
        final BookDto book = bookService.getBookById(id);
        log.debug("Book from db: {}", book);
        final List<CommentDto> comments = commentService.getAllCommentsByBookId(id);
        log.debug("Comment list from base by book id: {}", comments);
        model.addAttribute("book", book);
        model.addAttribute("comments", comments);
        return "book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        log.debug("Book id for delete: {}", id);
        bookService.deleteBookById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/books")
    public String books(){
        return "books";
    }
}

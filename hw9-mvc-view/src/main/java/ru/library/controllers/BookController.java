package ru.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.library.models.Author;
import ru.library.models.Book;
import ru.library.models.Genre;
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

    @GetMapping("/")
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "library";
    }

    @GetMapping("/edit-book")
    public String updateBook(@RequestParam("id") Long id,
                             Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/edit-book")
    public String saveBook(Book book,
                           Model model) {
        Book saveBook = bookService.save(book);
        model.addAttribute(saveBook);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") Long id,
                             Model model) {
        bookService.deleteBookById(id);
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "redirect:/";
    }

    @GetMapping("/add-book")
    public String saveBook(Model model) {
        List<Genre> genres = genreService.getAllGenre();
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "add-book";
    }

    @GetMapping("/book")
    public String showBook(@RequestParam("id") Long id,
                           Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book";
    }
}

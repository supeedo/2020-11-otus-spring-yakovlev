package ru.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.library.Dto.BookDto;
import ru.library.services.AuthorService;
import ru.library.services.BookServiceImpl;
import ru.library.services.GenreService;

import java.util.List;

@RestController
public class BookRestController {

    private final BookServiceImpl bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    @Autowired
    public BookRestController(BookServiceImpl bookService,
                              GenreService genreService,
                              AuthorService authorService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @GetMapping("/book")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showAllBooks() {
        final List<BookDto> books = bookService.getAllBooks();
        return books.isEmpty() ?
                ResponseEntity.ok().body(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(books);
    }

    @PutMapping("/book")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> saveBook(@RequestBody BookDto book) {
        bookService.save(book);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        final List<BookDto> books = bookService.getAllBooks();
        return books.isEmpty() ?
                ResponseEntity.ok(books) :
                ResponseEntity.ok().body(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showBook(@PathVariable Long id) {
        final BookDto book = bookService.getBookById(id);
        return book != null ? ResponseEntity.ok(book)
                : ResponseEntity.ok(HttpStatus.NOT_FOUND);
    }

}

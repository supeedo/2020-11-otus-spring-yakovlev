package ru.library.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.library.dto.BookDto;
import ru.library.services.BookServiceImpl;

import java.util.List;

@RestController
public class BookRestController {

    private static final Logger log = LoggerFactory.getLogger(BookRestController.class);

    private final BookServiceImpl bookService;

    @Autowired
    public BookRestController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDto>> showAllBooks() {
        final List<BookDto> books = bookService.getAllBooks();
        log.debug("Book list from base: {}", books);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/book")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> saveBook(@RequestBody BookDto book) {
        log.debug("Book for save: {}", book);
        bookService.save(book);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/book")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDto>> deleteBook(@RequestParam("id") Long id) {
        log.debug("id of the book to be deleted: {}", id);
        bookService.deleteBookById(id);
        final List<BookDto> books = bookService.getAllBooks();
        log.debug("Book list from base: {}", books);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDto> showBook(@PathVariable Long id) {
        log.debug("Book id: {}", id);
        final BookDto book = bookService.getBookById(id);
        log.debug("Book from db: {}", book);
        return ResponseEntity.ok(book);
    }

}

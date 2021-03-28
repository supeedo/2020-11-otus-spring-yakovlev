package ru.library.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.library.domain.Book;
import ru.library.repository.BookRepository;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookRepository bookRepository;
    
    @GetMapping("/books")
    public Flux<Book> findBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Mono<Book> findBookById(@PathVariable(name = "id") String id) {
        return bookRepository.findById(id);
    }
    
    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Book> createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
    
    @DeleteMapping("/books/{id}")
    public Mono<Void> deleteBookById(@PathVariable(name = "id") String id) {
        return bookRepository.deleteById(id);
    }

}

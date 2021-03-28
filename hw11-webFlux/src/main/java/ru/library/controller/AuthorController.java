package ru.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import ru.library.domain.Author;
import ru.library.repository.AuthorRepository;

@RequiredArgsConstructor
@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping("/authors")
    public Flux<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

}

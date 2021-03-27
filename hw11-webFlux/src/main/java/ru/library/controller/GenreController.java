package ru.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import ru.library.domain.Genre;
import ru.library.repository.GenreRepository;

@RequiredArgsConstructor
@RestController
public class GenreController {

    private final GenreRepository genreRepository;
    
    @GetMapping("/genres")
    public Flux<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

}

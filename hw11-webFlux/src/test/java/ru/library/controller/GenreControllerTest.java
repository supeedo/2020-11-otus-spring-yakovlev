package ru.library.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import ru.library.domain.Genre;
import ru.library.repository.GenreRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest {

    @MockBean
    private GenreRepository repository;
    
    @Autowired
    private GenreController genreController;
    
    private WebTestClient client = null; 

    private Genre russianClassic = Genre.builder().id("1000").name("Russian classic").build();
    private Genre foreignClassic = Genre.builder().id("1001").name("Foreign classic").build();

    @BeforeEach
    public void init() throws Exception {
        client = WebTestClient
                .bindToController(genreController)
                .build();
        
        Flux<Genre> result = Flux.create(sink -> {
            sink.next(russianClassic);
            sink.next(foreignClassic);
            sink.complete();
        });
        when(repository.findAll()).thenReturn(result);
    }

    @Test
    public void testFindAllGenres() throws Exception {
        client
        .get()
        .uri("/genres")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(Genre.class).hasSize(2).contains(russianClassic, foreignClassic);
    }

}

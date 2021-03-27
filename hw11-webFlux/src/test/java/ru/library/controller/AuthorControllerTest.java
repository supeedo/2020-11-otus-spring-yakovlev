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
import ru.library.domain.Author;
import ru.library.repository.AuthorRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    private static final String URI = "/authors";
    @MockBean
    private AuthorRepository repository;
    
    @Autowired
    private AuthorController authorController;
    
    private WebTestClient client = null; 

    private Author ivanov = Author.builder()
            .id("1000")
            .lastname("Ivanov")
            .firstname("Ivan")
            .middlename("Ivanovich")
            .build();
    private Author petrov = Author.builder()
            .id("1001")
            .lastname("Petrov")
            .firstname("Petr")
            .middlename("Petrovich")
            .build();

    @BeforeEach
    public void init() throws Exception {
        client = WebTestClient
                .bindToController(authorController)
                .build();
        
        Flux<Author> result = Flux.create(sink -> {
            sink.next(ivanov);
            sink.next(petrov);
            sink.complete();
        });
        when(repository.findAll()).thenReturn(result);
    }

    @Test
    public void testFindAllAuthors() throws Exception {
        client
            .get()
            .uri(URI)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBodyList(Author.class).hasSize(2).contains(ivanov, petrov);
    }
}

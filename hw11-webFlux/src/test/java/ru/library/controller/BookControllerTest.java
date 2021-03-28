package ru.library.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.library.domain.Author;
import ru.library.domain.Book;
import ru.library.domain.Genre;
import ru.library.repository.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    private static final String URI = "/books/";
    @MockBean
    private BookRepository repository;

    @Autowired
    private BookController bookController;

    private WebTestClient client = null;

    private Author authorLeoTolstoy = Author.builder()
            .id("1000")
            .lastname("Tolstoy")
            .firstname("Lev")
            .middlename("Nikolaevich")
            .build();

    private Genre russianClassic = Genre.builder().id("1000").name("Russian classics").build();

    private Book warAndPeace = Book.builder()
            .id("1000")
            .author(authorLeoTolstoy)
            .genre(russianClassic)
            .title("War and Peace")
            .build();
    private Book annaKarenina = Book.builder()
            .id("1001")
            .author(authorLeoTolstoy)
            .genre(russianClassic)
            .title("Anna Karenina")
            .build();

    @BeforeEach
    public void init() throws Exception {
        client = WebTestClient.bindToController(bookController).build();

        Flux<Book> result = Flux.create(sink -> {
            sink.next(warAndPeace);
            sink.next(annaKarenina);
            sink.complete();
        });
        when(repository.findAll()).thenReturn(result);
        when(repository.findById(warAndPeace.getId())).thenReturn(Mono.just(warAndPeace));
        when(repository.save(warAndPeace)).thenReturn(Mono.just(warAndPeace));
    }

    @Test
    public void testSaveBook() throws Exception {
        client.post()
                .uri(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(warAndPeace))
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(repository, times(1)).save(warAndPeace);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        client.get()
                .uri(URI)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Book.class)
                .hasSize(2)
                .contains(warAndPeace, annaKarenina);
    }

    @Test
    public void testGetBookById() throws Exception {
        client.get()
                .uri(URI + warAndPeace.getId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody()
                .equals(warAndPeace);
    }

}

package ru.library.repositories;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.library.models.Author;
import ru.library.models.Book;
import ru.library.models.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
@Import(BookRepositoriesJpa.class)
class BookRepositoriesJpaTest {

    private static final int EXPECTED_BOOKS_COUNT = 2;
    private static final long FIRST_ID = 1L;
    private static final long SECOND_ID = 2L;
    private static final long NEW_BOOK_ID = 3L;
    private static final String TEST_BOOK_TITLE = "Test book title";
    private static final String UPDATE_BOOK_TITLE = "Update book title";

    @Autowired
    private BookRepositories bookRepo;

    @Autowired
    private TestEntityManager tem;

    @DisplayName("The number of books is as expected")
    @Test
    void getBooksCount() {
        final long actualBooksCount = bookRepo.getBooksCount();
        Assertions.assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("The inserted book is as expected")
    @Test
    void insertBook() {
        final Author author = tem.find(Author.class, FIRST_ID);
        final Genre genre = tem.find(Genre.class, FIRST_ID);
        final Book bookForInsert = new Book(NEW_BOOK_ID, TEST_BOOK_TITLE,
                author,
                genre);
        bookRepo.insertBook(bookForInsert);
        final Book actualBook = tem.find(Book.class, NEW_BOOK_ID);
        Assertions.assertThat(actualBook).usingRecursiveComparison().isEqualTo(bookForInsert);
    }

    @DisplayName("Updated book is as expected")
    @Test
    void updateBook() {
        final Author author = tem.find(Author.class, SECOND_ID);
        final Genre genre = tem.find(Genre.class, SECOND_ID);
        final Book expectedBook = tem.find(Book.class, FIRST_ID);
        expectedBook.setBookTitle(UPDATE_BOOK_TITLE);
        expectedBook.setAuthor(author);
        expectedBook.setGenre(genre);
        bookRepo.updateBook(expectedBook);
        final Book updatedBook = tem.find(Book.class, FIRST_ID);
        Assertions.assertThat(updatedBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Book with the specified ID removed")
    @Test
    void deleteBookById() {
        bookRepo.deleteBookById(FIRST_ID);
        assertNull(tem.find(Book.class, FIRST_ID));
    }

    @DisplayName("The book received from the ID corresponds to the expected")
    @Test
    void getBookById() {
        final Book expectedBook = tem.find(Book.class, FIRST_ID);
        final Book actualBook = bookRepo.getBookById(FIRST_ID).get();
        Assertions.assertThat(expectedBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @DisplayName("The resulting list of all books is as expected")
    @Test
    void getAllBooks() {
        final List<Book> expectedBooksList = List.of(
                tem.find(Book.class, FIRST_ID),
                tem.find(Book.class, SECOND_ID)
        );
        final List<Book> actualBooksList = bookRepo.getAllBooks();
        Assertions.assertThat(expectedBooksList.get(0)).usingRecursiveComparison()
                .isEqualTo(actualBooksList.get(0));
        Assertions.assertThat(expectedBooksList.get(1)).usingRecursiveComparison()
                .isEqualTo(actualBooksList.get(1));
    }

    @DisplayName("The resulting list of titles is as expected")
    @Test
    void getTitles() {
        final List<String> expectedTitleBook = List.of("id", "Book name", "Author full name", "Genre");
        final List<String> actualTitleBook = bookRepo.getTitles();
        Assertions.assertThat(expectedTitleBook).containsExactlyInAnyOrderElementsOf(actualTitleBook);

    }
}
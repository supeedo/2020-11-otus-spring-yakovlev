package ru.library.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.library.dto.Author;
import ru.library.dto.Book;
import ru.library.dto.Genre;

import java.util.List;

@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 2;

    @Autowired
    private BookDao bookDao;

    @DisplayName("The number of books is as expected")
    @Test
    void getBooksCount() {
        final int actualBooksCount = bookDao.getBooksCount();
        Assertions.assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("The inserted book is as expected")
    @Test
    void insertBook() {
        final Book expectedBook = new Book(123L, "New book",
                new Author(1L, "Robert Martin"), new Genre(1L, "Computer science"));
        bookDao.insertBook(expectedBook);
        final Book actualBook = bookDao.getBookById(expectedBook.getId());
        Assertions.assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Updated book is as expected")
    @Test
    void updateBook() {
        final Book expectedBook = new Book(1L, "Update book",
                new Author(1L, "Robert Martin"), new Genre(1L, "Computer science"));
        bookDao.updateBook(expectedBook);
        final Book updatedBook = bookDao.getBookById(expectedBook.getId());
        Assertions.assertThat(updatedBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Book with the specified ID removed")
    @Test
    void deleteBookById() {
        final long bookIdForDelete = 1L;
        bookDao.deleteBookById(bookIdForDelete);
        Assertions
                .assertThatThrownBy(() -> bookDao.getBookById(bookIdForDelete))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("The book received from the ID corresponds to the expected")
    @Test
    void getBookById() {
        final Book expectedBook = new Book(1L, "Clean Code",
                new Author(1L, "Robert Martin"), new Genre(1L, "Computer science"));
        final Book actualBook = bookDao.getBookById(expectedBook.getId());
        Assertions.assertThat(expectedBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @DisplayName("The resulting list of all books is as expected")
    @Test
    void getAllBooks() {
        final List<Book> expectedBooksList = List.of(
                new Book(1L, "Clean Code",
                        new Author(1L, "Robert Martin"), new Genre(1L, "Computer science")),
                new Book(2L, "Effective Java",
                        new Author(2L, "Joshua Bloch"), new Genre(1L, "Computer science"))
        );
        final List<Book> actualBooksList = bookDao.getAllBooks();
        Assertions.assertThat(expectedBooksList.get(0)).usingRecursiveComparison()
                .isEqualTo(actualBooksList.get(0));
        Assertions.assertThat(expectedBooksList.get(1)).usingRecursiveComparison()
                .isEqualTo(actualBooksList.get(1));
    }

    @DisplayName("The resulting list of titles is as expected")
    @Test
    void getTitles() {
        final List<String> expectedTitleBook = List.of("id", "Book name", "Author full name", "Genre");
        final List<String> actualTitleBook = bookDao.getTitles();
        Assertions.assertThat(expectedTitleBook).containsExactlyInAnyOrderElementsOf(actualTitleBook);

    }
}
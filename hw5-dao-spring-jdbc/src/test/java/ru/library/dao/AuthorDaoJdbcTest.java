package ru.library.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.library.dto.Author;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private static final int EXPECTED_AUTHORS_COUNT = 2;

    @Autowired
    private AuthorDao authorDao;

    @DisplayName("The number of authors is as expected")
    @Test
    void getAuthorsCount() {
        final int actualAuthorsCount = authorDao.getAuthorsCount();
        Assertions.assertThat(actualAuthorsCount).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("The inserted author is as expected")
    @Test
    void insertAuthor() {
        final Author expectedAuthor = new Author(123L, "Test Author");
        authorDao.insertAuthor(expectedAuthor);
        Author actualAuthor = authorDao.getAuthorById(expectedAuthor.getId());
        Assertions.assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("Updated author is as expected")
    @Test
    void updateAuthor() {
        final Author expectedAuthor = new Author(1L, "Update Author");
        authorDao.updateAuthor(expectedAuthor);
        Author updatedAuthor = authorDao.getAuthorById(expectedAuthor.getId());
        Assertions.assertThat(updatedAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("Author with the specified ID removed")
    @Test
    void deleteAuthorById() {
        final long authorIdForDelete = 1L;
        authorDao.deleteAuthorById(authorIdForDelete);
        Assertions
                .assertThatThrownBy(() -> authorDao.getAuthorById(authorIdForDelete))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("The author received from the ID corresponds to the expected")
    @Test
    void getAuthorById() {
        final Author expectedAuthor = new Author(1L, "Robert Martin");
        final Author actualAuthor = authorDao.getAuthorById(expectedAuthor.getId());
        Assertions.assertThat(expectedAuthor).usingRecursiveComparison().isEqualTo(actualAuthor);
    }

    @DisplayName("The resulting list of all authors is as expected")
    @Test
    void getAllAuthors() {
        final List<Author> expectedAuthorsList = List.of(
                new Author(1L, "Robert Martin"),
                new Author(2L, "Joshua Bloch")
        );
        final List<Author> actualAuthorsList = authorDao.getAllAuthors();
        Assertions.assertThat(expectedAuthorsList.get(0)).usingRecursiveComparison()
                .isEqualTo(actualAuthorsList.get(0));
        Assertions.assertThat(expectedAuthorsList.get(1)).usingRecursiveComparison()
                .isEqualTo(actualAuthorsList.get(1));
    }

    @DisplayName("The resulting list of titles is as expected")
    @Test
    void getTitles() {
        List<String> expectedTitleAuthor = List.of("id", "Author full name");
        List<String> actualTitleAuthor = authorDao.getTitles();
        Assertions.assertThat(expectedTitleAuthor).containsExactlyInAnyOrderElementsOf(actualTitleAuthor);
    }
}
package ru.library.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.library.models.Author;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(AuthorRepositoriesJpa.class)
class AuthorRepositoriesJpaTest {

    private static final int EXPECTED_AUTHORS_COUNT = 2;
    private static final long FIRST_AUTHORS_ID = 1L;
    private static final long SECOND_AUTHORS_ID = 2L;
    private static final long NEW_AUTHORS_ID = 3L;
    private static final String TEST_AUTHORS_NAME = "Test author name";
    private static final String UPDATE_AUTHORS_NAME = "Update author name";

    @Autowired
    private AuthorRepositories authorDao;

    @Autowired
    private TestEntityManager tem;

    @DisplayName("The number of authors is as expected")
    @Test
    void getAuthorsCount() {
        final long actualAuthorsCount = authorDao.getAuthorsCount();
        Assertions.assertThat(actualAuthorsCount).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("The inserted author is as expected")
    @Test
    void insertAuthor() {
        final Author expectedAuthor = new Author(NEW_AUTHORS_ID, TEST_AUTHORS_NAME);
        authorDao.insertAuthor(expectedAuthor);
        final Author actualAuthor = tem.find(Author.class, NEW_AUTHORS_ID);
        Assertions.assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("Updated author is as expected")
    @Test
    void updateAuthor() {
        final Author actualAuthor = tem.find(Author.class, FIRST_AUTHORS_ID);
        tem.clear();
        final Author expectedAuthor = new Author(FIRST_AUTHORS_ID, "Update author name");
        authorDao.updateAuthorById(expectedAuthor);
        final Author updatedAuthor = tem.find(Author.class, FIRST_AUTHORS_ID);
        Assertions.assertThat(updatedAuthor).usingRecursiveComparison().isNotEqualTo(actualAuthor);
        Assertions.assertThat(updatedAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("Author with the specified ID removed")
    @Test
    void deleteAuthorById() {
        authorDao.deleteAuthorById(FIRST_AUTHORS_ID);
        assertNull(tem.find(Author.class, FIRST_AUTHORS_ID));
    }

    @DisplayName("The author received from the ID corresponds to the expected")
    @Test
    void getAuthorById() {
        final Author expectedAuthor = tem.find(Author.class, FIRST_AUTHORS_ID);
        final Author actualAuthor = (authorDao.getAuthorById(FIRST_AUTHORS_ID)).get();
        Assertions.assertThat(expectedAuthor).usingRecursiveComparison().isEqualTo(actualAuthor);
    }

    @DisplayName("The resulting list of all authors is as expected")
    @Test
    void getAllAuthors() {
        final List<Author> expectedAuthorsList = List.of(
                tem.find(Author.class, FIRST_AUTHORS_ID),
                tem.find(Author.class, SECOND_AUTHORS_ID)
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
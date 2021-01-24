package ru.library.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.library.models.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(GenreRepositoriesJpa.class)
class GenreRepositoriesJpaTest {

    private static final int EXPECTED_GENRES_COUNT = 2;
    private static final long FIRST_GENRE_ID = 1L;
    private static final long SECOND_GENRE_ID = 2L;
    private static final long NEW_GENRE_ID = 3L;
    private static final String TEST_GENRE_NAME = "Test genre name";
    private static final String UPDATE_GENRE_NAME = "Update genre name";


    @Autowired
    private GenreRepositories genreDao;

    @Autowired
    private TestEntityManager tem;

    @DisplayName("The number of genres is as expected")
    @Test
    void getGenreCount() {
        final int actualGenresCount = genreDao.getGenreCount();
        Assertions.assertThat(actualGenresCount).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("The inserted genre is as expected")
    @Test
    void insertGenre() {
        final Genre expectedGenre = new Genre(NEW_GENRE_ID, TEST_GENRE_NAME);
        genreDao.insertGenre(expectedGenre);
        final Genre actualGenre = tem.find(Genre.class, NEW_GENRE_ID);
        Assertions.assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("Updated genre is as expected")
    @Test
    void updateGenre() {
        final Genre actualGenre = tem.find(Genre.class, FIRST_GENRE_ID);
        final Genre expectedGenre = new Genre(FIRST_GENRE_ID, UPDATE_GENRE_NAME);
        genreDao.updateGenre(expectedGenre);
        tem.clear();
        final Genre updatedGenre = tem.find(Genre.class, FIRST_GENRE_ID);
        Assertions.assertThat(updatedGenre).usingRecursiveComparison().isNotEqualTo(actualGenre);
        Assertions.assertThat(updatedGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("Genre with the specified ID removed")
    @Test
    void deleteGenreById() {
        genreDao.deleteGenreById(FIRST_GENRE_ID);
        assertNull(tem.find(Genre.class, FIRST_GENRE_ID));
    }

    @DisplayName("The genre received from the ID corresponds to the expected")
    @Test
    void getGenreById() {
        final Genre expectedGenre = tem.find(Genre.class, FIRST_GENRE_ID);
        final Genre actualGenre = genreDao.getGenreById(FIRST_GENRE_ID);
        Assertions.assertThat(expectedGenre).usingRecursiveComparison().isEqualTo(actualGenre);
    }

    @DisplayName("The resulting list of all genres is as expected")
    @Test
    void getAllGenres() {
        final List<Genre> expectedGenresList = List.of(
                tem.find(Genre.class, FIRST_GENRE_ID),
                tem.find(Genre.class, SECOND_GENRE_ID)
        );
        final List<Genre> actualGenresList = genreDao.getAllGenres();
        Assertions.assertThat(expectedGenresList.get(0)).usingRecursiveComparison()
                .isEqualTo(actualGenresList.get(0));
        Assertions.assertThat(expectedGenresList.get(1)).usingRecursiveComparison()
                .isEqualTo(actualGenresList.get(1));
    }

    @DisplayName("The resulting list of titles is as expected")
    @Test
    void getTitles() {
        final List<String> expectedTitleGenre = List.of("id", "Genre name");
        final List<String> actualTitleGenre = genreDao.getTitles();
        Assertions.assertThat(expectedTitleGenre).containsExactlyInAnyOrderElementsOf(actualTitleGenre);
    }
}
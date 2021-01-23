package ru.library.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.library.models.Genre;

import java.util.List;

@DataJpaTest
@Import(GenreRepositoriesJpa.class)
class GenreRepositoriesJpaTest {

    private static final int EXPECTED_GENRES_COUNT = 2;

    @Autowired
    private GenreRepositories genreDao;

    @DisplayName("The number of genres is as expected")
    @Test
    void getGenreCount() {
        final int actualGenresCount = genreDao.getGenreCount();
        Assertions.assertThat(actualGenresCount).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("The inserted genre is as expected")
    @Test
    void insertGenre() {
        final Genre expectedGenre = new Genre(123L, "Test genre name");
        genreDao.insertGenre(expectedGenre);
        final Genre actualGenre = genreDao.getGenreById(expectedGenre.getId());
        Assertions.assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("Updated genre is as expected")
    @Test
    void updateGenre() {
        final Genre expectedGenre = new Genre(1L, "Update genre");
        genreDao.updateGenre(expectedGenre);
        final Genre updatedGenre = genreDao.getGenreById(expectedGenre.getId());
        Assertions.assertThat(updatedGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("Genre with the specified ID removed")
    @Test
    void deleteGenreById() {
        final long genreIdForDelete = 1L;
        genreDao.deleteGenreById(genreIdForDelete);
        Assertions
                .assertThatThrownBy(() -> genreDao.getGenreById(genreIdForDelete))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("The genre received from the ID corresponds to the expected")
    @Test
    void getGenreById() {
        final Genre expectedGenre = new Genre(1L, "Computer science");
        final Genre actualGenre = genreDao.getGenreById(expectedGenre.getId());
        Assertions.assertThat(expectedGenre).usingRecursiveComparison().isEqualTo(actualGenre);
    }

    @DisplayName("The resulting list of all genres is as expected")
    @Test
    void getAllGenres() {
        final List<Genre> expectedGenresList = List.of(
                new Genre(1L, "Computer science"),
                new Genre(2L, "Science Fiction")
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
package ru.library.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.library.Dto.AuthorDto;
import ru.library.Dto.GenreDto;
import ru.library.models.Genre;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class GenreServiceImplTest {

    private static final long EXPECTED_COUNT_GENRES_IN_BASE = 2L;
    private static final GenreDto FIRST_EXPECTED_GENRE = new GenreDto(1L, "Computer science");
    private static final GenreDto SECOND_EXPECTED_GENRE = new GenreDto(2L, "Science Fiction");
    private static final String UPDATE_GENRE_NAME = "UpdateName";
    private static final long ID_FIRST_GENRE = 1L;

    @Autowired
    private GenreServiceImpl service;

    @Test
    @DisplayName("Expected quantity is as expected")
    void getCount() {
        final long count = service.getCount();
        assertThat(count).isEqualTo(EXPECTED_COUNT_GENRES_IN_BASE);
    }

    @Test
    @DisplayName("Getting all items as expected")
    void getAllGenre() {
        final List<GenreDto> expectedList = new ArrayList<>(Arrays.asList(FIRST_EXPECTED_GENRE, SECOND_EXPECTED_GENRE));
        final List<GenreDto> actualList = service.getAllGenre();

        assertThat(actualList)
                .isNotEmpty()
                .hasSize((int) EXPECTED_COUNT_GENRES_IN_BASE)
                .doesNotHaveDuplicates()
                .containsAll(expectedList);
    }

    @Test
    @DisplayName("Getting an item by ID matches what was expected")
    void getGenreById() {
        final GenreDto actualDto = service.getGenreById(ID_FIRST_GENRE);
        assertThat(actualDto)
                .isNotNull()
                .isEqualTo(FIRST_EXPECTED_GENRE)
                .isNotEqualTo(SECOND_EXPECTED_GENRE);
    }

    @Test
    @DisplayName("Deleting an item by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteGenreById() {
        service.deleteGenreById(ID_FIRST_GENRE);
        assertThat(service.getAllGenre()).hasSizeLessThan((int) EXPECTED_COUNT_GENRES_IN_BASE);
        assertThrows(EntityNotFoundException.class, () -> service.getGenreById(ID_FIRST_GENRE));
    }

    @Test
    @DisplayName("Adding a new item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewGenre() {
        final GenreDto genreForCreate = new GenreDto(UPDATE_GENRE_NAME);
        service.createNewGenre(genreForCreate);
        assertThat(service.getAllGenre()).hasSizeGreaterThan((int) EXPECTED_COUNT_GENRES_IN_BASE);
    }

    @Test
    @DisplayName("Item update occurs as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateGenre() {
        GenreDto genreForUpdateDto = service.getGenreById(ID_FIRST_GENRE);
        genreForUpdateDto.setGenreName(UPDATE_GENRE_NAME);
        service.updateGenre(genreForUpdateDto);
        assertThat(service.getGenreById(ID_FIRST_GENRE))
                .isNotNull()
                .isEqualTo(genreForUpdateDto)
                .isNotEqualTo(SECOND_EXPECTED_GENRE);
    }

    @Test
    @DisplayName("Save item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void save() {
        final GenreDto genreForCreate = new GenreDto(UPDATE_GENRE_NAME);
        service.save(genreForCreate);
        assertThat(service.getAllGenre()).hasSizeGreaterThan((int) EXPECTED_COUNT_GENRES_IN_BASE);
    }
}
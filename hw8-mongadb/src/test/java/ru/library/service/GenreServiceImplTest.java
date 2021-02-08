package ru.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
class GenreServiceImplTest {
    private static final String EXPECTED_COUNT_GENRES_IN_BASE = "There are 2 genres in the library";
    private static final String FIRST_EXPECTED_GENRE = "Computer science";
    private static final String SECOND_EXPECTED_GENRE = "Science Fiction";
    private static final String UPDATE_GENRE_NAME = "UpdateName";
    private static final String ID_FIRST_GENRE = "1";

    @Autowired
    private GenreServiceImpl service;

    @Test
    @DisplayName("Expected quantity is as expected")
    void getCount() {
        final String count = service.getCount();
        assertThat(count).isEqualTo(EXPECTED_COUNT_GENRES_IN_BASE);
    }

    @Test
    @DisplayName("Getting all items as expected")
    void getAllGenre() {
        final String actualMessage = service.getAllGenre();
        assertThat(actualMessage)
                .contains(FIRST_EXPECTED_GENRE)
                .contains(SECOND_EXPECTED_GENRE);
    }

    @Test
    @DisplayName("Getting an item by ID matches what was expected")
    void getGenreById() {
        final String response = service.getGenreById(ID_FIRST_GENRE);
        assertThat(response)
                .contains(ID_FIRST_GENRE)
                .contains(FIRST_EXPECTED_GENRE);
    }

    @Test
    @DisplayName("Deleting an item by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteGenreById() {
        final String expectedResult = String.format("Genre with id: %s has delete", ID_FIRST_GENRE);
        final String response = service.deleteGenreById(ID_FIRST_GENRE);
        assertThat(response)
                .isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Adding a new item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewGenre() {
        final String expectedResult = "Genre has insert";
        final String response = service.createNewGenre(UPDATE_GENRE_NAME);
        assertThat(response).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("The resulting list is as expected")
    void getTitles() {
        final List<String> expectedTitleList = List.of("id", "Genre name");
        final List<String> actualTitleList = service.getTitles();
        assertThat(expectedTitleList).containsAll(actualTitleList);
    }
}
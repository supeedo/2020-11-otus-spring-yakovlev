package ru.library.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
class GenreShellCommandTest {

    private static final int COUNT_GENRES_IN_BASE = 2;

    private static final String GENRE_COUNT = "genres count";
    private static final String COUNT_GENRES = "count genres";
    private static final String SHOW_ALL_GENRES = "show all genres";
    private static final String ALL_GENRES = "all genres";
    private static final String GENRE_ID = "genre id";
    private static final String DELETE_GENRE = "delete genre";
    private static final String CREATE_GENRE = "create genre";
    private static final String GENRE_CREATE = "genre create";
    private static final String UPDATE_GENRE = "update genre";

    private static final String FIRST_EXPECTED_GENRE = "Computer science";
    private static final String SECOND_EXPECTED_GENRE = "Science Fiction";
    private static final String UPDATE_GENRE_NAME = "UpdateName";
    private static final String ID_FIRST_GENRE = "1";

    @Autowired
    private Shell shell;

    @Test
    @DisplayName("")
    void showGenresCount() {
        final String expectedResult = String.format("There are %d genres in the library", COUNT_GENRES_IN_BASE);
        final String resFirst = (String) shell.evaluate(() -> GENRE_COUNT);
        final String resSecond = (String) shell.evaluate(() -> COUNT_GENRES);
        assertThat(expectedResult)
                .isEqualTo(resFirst)
                .isEqualTo(resSecond);
    }

    @Test
    @DisplayName("")
    void showAllGenres() {
        final String resFirst = (String) shell.evaluate(() -> SHOW_ALL_GENRES);
        final String resSecond = (String) shell.evaluate(() -> ALL_GENRES);
        assertThat(resFirst)
                .contains(FIRST_EXPECTED_GENRE)
                .contains(SECOND_EXPECTED_GENRE);
        assertThat(resSecond)
                .contains(FIRST_EXPECTED_GENRE)
                .contains(SECOND_EXPECTED_GENRE);
    }

    @Test
    @DisplayName("")
    void showGenreById() {
        final String request = String.format("%s %s", GENRE_ID, ID_FIRST_GENRE);
        final String response = (String) shell.evaluate(() -> request);
        assertThat(response)
                .contains(ID_FIRST_GENRE)
                .contains(FIRST_EXPECTED_GENRE);
    }

    @Test
    @DisplayName("")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteGenreById() {
        final String expectedResult = String.format("Genre with id: %s has delete", ID_FIRST_GENRE);
        final String request = String.format("%s %s", DELETE_GENRE, ID_FIRST_GENRE);
        final String response = (String) shell.evaluate(() -> request);
        assertThat(response)
                .isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("")
    void insertNewGenre() {
        final String expectedResult = "Genre has insert";
        final String firstRequest = String.format("%s %s", CREATE_GENRE, UPDATE_GENRE_NAME);
        final String secondRequest = String.format("%s %s", GENRE_CREATE, UPDATE_GENRE_NAME);
        final String resFirst = (String) shell.evaluate(() -> firstRequest);
        final String resSecond = (String) shell.evaluate(() -> secondRequest);
        assertThat(resFirst).isEqualTo(expectedResult);
        assertThat(resSecond).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateGenre() {
        final String expectedStartResult = "Genre";
        final String expectedEndResult = "has update";
        final String request = String.format("%s %s %s", UPDATE_GENRE, ID_FIRST_GENRE, UPDATE_GENRE_NAME);
        final String res = (String) shell.evaluate(() -> request);
        assertThat(res)
                .startsWith(expectedStartResult)
                .contains(UPDATE_GENRE_NAME)
                .endsWith(expectedEndResult);
    }
}
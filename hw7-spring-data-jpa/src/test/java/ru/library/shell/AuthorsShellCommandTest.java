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
class AuthorsShellCommandTest {

    private static final int COUNT_AUTHORS_IN_BASE = 2;

    private static final String AUTHORS_COUNT = "authors count";
    private static final String COUNT_AUTHORS = "count authors";
    private static final String SHOW_ALL_AUTHORS = "show all authors";
    private static final String ALL_AUTHORS = "all authors";
    private static final String AUTHOR_ID = "author id";
    private static final String AUTHOR_NAME = "author name";
    private static final String DELETE_AUTHOR = "delete author";
    private static final String CREATE_AUTHOR = "create author";
    private static final String AUTHOR_CREATE = "author create";
    private static final String UPDATE_AUTHOR = "update author";

    private static final String FIRST_EXPECTED_AUTHOR = "Robert Martin";
    private static final String SECOND_EXPECTED_AUTHOR = "Joshua Bloch";
    private static final String UPDATE_AUTHOR_NAME = "UpdateName";
    private static final String ID_FIRST_AUTHOR = "1";

    @Autowired
    private Shell shell;

    @Test
    void showAuthorCount() {
        final String expectedResult = String.format("There are %d authors in the library", COUNT_AUTHORS_IN_BASE);
        final String resFirst = (String) shell.evaluate(() -> AUTHORS_COUNT);
        final String resSecond = (String) shell.evaluate(() -> COUNT_AUTHORS);
        assertThat(expectedResult)
                .isEqualTo(resFirst)
                .isEqualTo(resSecond);
    }

    @Test
    void showAllAuthors() {
        final String resFirst = (String) shell.evaluate(() -> SHOW_ALL_AUTHORS);
        final String resSecond = (String) shell.evaluate(() -> ALL_AUTHORS);
        assertThat(resFirst)
                .contains(FIRST_EXPECTED_AUTHOR)
                .contains(SECOND_EXPECTED_AUTHOR);
        assertThat(resSecond)
                .contains(FIRST_EXPECTED_AUTHOR)
                .contains(SECOND_EXPECTED_AUTHOR);
    }

    @Test
    void showAuthorById() {
        final String request = String.format("%s %s", AUTHOR_ID, ID_FIRST_AUTHOR);
        final String response = (String) shell.evaluate(() -> request);
        assertThat(response)
                .contains(ID_FIRST_AUTHOR)
                .contains(FIRST_EXPECTED_AUTHOR);
    }

    @Test
    void showAuthorsByName() {
        final String request = String.format("%s %s", AUTHOR_NAME,
                FIRST_EXPECTED_AUTHOR.substring(0,6));
        final String response = (String) shell.evaluate(() -> request);
        assertThat(response)
                .contains(ID_FIRST_AUTHOR)
                .contains(FIRST_EXPECTED_AUTHOR);
    }

    @Test
    @DisplayName("")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteAuthorById() {
        final String expectedResult = String.format("Author with id: %s has delete", ID_FIRST_AUTHOR);
        final String request = String.format("%s %s", DELETE_AUTHOR, ID_FIRST_AUTHOR);
        final String response = (String) shell.evaluate(() -> request);
        assertThat(response)
                .isEqualTo(expectedResult);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void insertNewAuthor() {
        final String expectedResult = "Author has insert";
        final String firstRequest = String.format("%s %s", CREATE_AUTHOR, ID_FIRST_AUTHOR);
        final String secondRequest = String.format("%s %s", AUTHOR_CREATE, ID_FIRST_AUTHOR);
        final String resFirst = (String) shell.evaluate(() -> firstRequest);
        final String resSecond = (String) shell.evaluate(() -> secondRequest);
        assertThat(resFirst).isEqualTo(expectedResult);
        assertThat(resSecond).isEqualTo(expectedResult);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateAuthor() {
        final String expectedStartResult = "Author";
        final String expectedEndResult = "has update";
        final String request = String.format("%s %s %s", UPDATE_AUTHOR, ID_FIRST_AUTHOR, UPDATE_AUTHOR_NAME);
        final String res = (String) shell.evaluate(() -> request);
        assertThat(res)
                .startsWith(expectedStartResult)
                .contains(UPDATE_AUTHOR_NAME)
                .endsWith(expectedEndResult);
    }
}
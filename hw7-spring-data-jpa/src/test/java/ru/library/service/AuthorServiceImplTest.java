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
class AuthorServiceImplTest {
    private static final String EXPECTED_COUNT_AUTHORS_IN_BASE = "There are 2 authors in the library";
    private static final String FIRST_EXPECTED_AUTHOR = "Robert Martin";
    private static final String SECOND_EXPECTED_AUTHOR = "Joshua Bloch";
    private static final String UPDATE_AUTHOR_NAME = "UpdateName";
    private static final String ID_FIRST_AUTHOR = "1";

    @Autowired
    private AuthorServiceImpl service;

    @Test
    @DisplayName("Expected quantity is as expected")
    void getCount() {
        final String count = service.getCount();
        assertThat(count).isEqualTo(EXPECTED_COUNT_AUTHORS_IN_BASE);
    }

    @Test
    @DisplayName("Getting all items as expected")
    void getAllAuthors() {
        final String actualMessage = service.getAllAuthors();
        assertThat(actualMessage)
                .contains(FIRST_EXPECTED_AUTHOR)
                .contains(SECOND_EXPECTED_AUTHOR);
    }

    @Test
    @DisplayName("Getting an item by ID matches what was expected")
    void getAuthorById() {
        final String response = service.getAuthorById(Long.parseLong(ID_FIRST_AUTHOR));
        assertThat(response)
                .contains(ID_FIRST_AUTHOR)
                .contains(FIRST_EXPECTED_AUTHOR);
    }

    @Test
    @DisplayName("Getting an item by name matches what was expected")
    void getAuthorsByName() {
        final String response = service.getAuthorsByName(FIRST_EXPECTED_AUTHOR);
        assertThat(response)
                .contains(ID_FIRST_AUTHOR)
                .contains(FIRST_EXPECTED_AUTHOR);
    }

    @Test
    @DisplayName("Deleting an item by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteAuthorById() {
        final String expectedResult = String.format("Author with id: %s has delete", ID_FIRST_AUTHOR);
        final String response = service.deleteAuthorById(Long.parseLong(ID_FIRST_AUTHOR));
        assertThat(response)
                .isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Adding a new item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewAuthor() {
        final String expectedResult = "Author has insert";
        final String response = service.createNewAuthor(UPDATE_AUTHOR_NAME);
        assertThat(response).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Item update occurs as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateAuthor() {
        final String expectedStartResult = "Author";
        final String expectedEndResult = "has update";
        final String response = service.updateAuthor(Long.parseLong(ID_FIRST_AUTHOR), UPDATE_AUTHOR_NAME);
        assertThat(response)
                .startsWith(expectedStartResult)
                .contains(UPDATE_AUTHOR_NAME)
                .endsWith(expectedEndResult);
    }

    @Test
    @DisplayName("The resulting list is as expected")
    void getTitles() {
        final List<String> expectedTitleList = List.of("id", "Author full name");
        final List<String> actualTitleList = service.getTitles();
        assertThat(expectedTitleList).containsAll(actualTitleList);
    }
}
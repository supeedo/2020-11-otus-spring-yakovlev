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
class BookServiceImplTest {
    private static final String EXPECTED_COUNT_AUTHORS_IN_BASE = "There are 2 books in the library";
    private static final String FIRST_EXPECTED_BOOK = "Clean Code";
    private static final String SECOND_EXPECTED_BOOK = "Effective Java";
    private static final String UPDATE_BOOK_NAME = "UpdateName";
    private static final String ID_FIRST_AUTHOR = "1";
    private static final String ID_FIRST_GENRE = "1";

    @Autowired
    private BookServiceImpl service;

    @Test
    @DisplayName("Expected quantity is as expected")
    void getCount() {
        final String count = service.getCount();
        assertThat(count).isEqualTo(EXPECTED_COUNT_AUTHORS_IN_BASE);
    }

    @Test
    @DisplayName("Getting all items as expected")
    void getAllBooks() {
        final String actualMessage = service.getAllBooks();
        assertThat(actualMessage)
                .contains(FIRST_EXPECTED_BOOK)
                .contains(SECOND_EXPECTED_BOOK);
    }

    @Test
    @DisplayName("Adding a new item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewBook() {
        final String expectedResult = "Book has insert";
        final String response = service.createNewBook(UPDATE_BOOK_NAME, ID_FIRST_AUTHOR, ID_FIRST_GENRE );
        assertThat(response).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("The resulting list is as expected")
    void getTitles() {
        final List<String> expectedTitleList = List.of("id", "Book name", "Author full name", "Genre");
        final List<String> actualTitleList = service.getTitles();
        assertThat(expectedTitleList).containsAll(actualTitleList);
    }
}
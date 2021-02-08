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
class BooksShellCommandTest {

    private static final int COUNT_BOOKS_IN_BASE = 2;

    private static final String BOOKS_COUNT = "books count";
    private static final String COUNT_BOOKS = "count books";
    private static final String SHOW_ALL_BOOKS = "show all books";
    private static final String ALL_BOOKS = "all books";
    private static final String BOOK_ID = "book id";
    private static final String DELETE_BOOK = "delete book";
    private static final String CREATE_BOOK = "create book";
    private static final String BOOK_CREATE = "book create";
    private static final String UPDATE_BOOK = "update book";

    private static final String FIRST_EXPECTED_BOOK = "Clean Code";
    private static final String SECOND_EXPECTED_BOOK = "Effective Java";
    private static final String UPDATE_BOOK_NAME = "UpdateName";
    private static final String ID_FIRST_BOOK = "1";
    private static final String ID_FIRST_AUTHOR = "1";
    private static final String ID_FIRST_GENRE = "1";

    @Autowired
    private Shell shell;

    @Test
    @DisplayName("Expected quantity is as expected")
    void showBooksCount() {
        final String expectedResult = String.format("There are %d books in the library", COUNT_BOOKS_IN_BASE);
        final String resFirst = (String) shell.evaluate(() -> BOOKS_COUNT);
        final String resSecond = (String) shell.evaluate(() -> COUNT_BOOKS);
        assertThat(expectedResult)
                .isEqualTo(resFirst)
                .isEqualTo(resSecond);
    }

    @Test
    @DisplayName("Getting all items as expected")
    void showAllBooks() {
        final String resFirst = (String) shell.evaluate(() -> SHOW_ALL_BOOKS);
        final String resSecond = (String) shell.evaluate(() -> ALL_BOOKS);
        assertThat(resFirst)
                .contains(FIRST_EXPECTED_BOOK)
                .contains(SECOND_EXPECTED_BOOK);
        assertThat(resSecond)
                .contains(FIRST_EXPECTED_BOOK)
                .contains(SECOND_EXPECTED_BOOK);
    }

    @Test
    @DisplayName("Getting an item by ID matches what was expected")
    void showBookById() {
        final String request = String.format("%s %s", BOOK_ID, ID_FIRST_BOOK);
        final String response = (String) shell.evaluate(() -> request);
        assertThat(response)
                .contains(ID_FIRST_BOOK)
                .contains(FIRST_EXPECTED_BOOK);
    }

    @Test
    @DisplayName("Deleting an item by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteBookById() {
        final String expectedResult = String.format("Book with id: %s has delete", ID_FIRST_BOOK);
        final String request = String.format("%s %s", DELETE_BOOK, ID_FIRST_BOOK);
        final String response = (String) shell.evaluate(() -> request);
        assertThat(response)
                .isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Adding a new item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void insertNewBook() {
        final String expectedResult = "Book has insert";
        final String firstRequest = String.format("%s %s %s %s",
                CREATE_BOOK, UPDATE_BOOK_NAME, ID_FIRST_AUTHOR,ID_FIRST_GENRE);
        final String secondRequest = String.format("%s %s %s %s",
                BOOK_CREATE, UPDATE_BOOK_NAME, ID_FIRST_AUTHOR,ID_FIRST_GENRE);
        final String responseFirst = (String) shell.evaluate(() -> firstRequest);
        final String responseSecond = (String) shell.evaluate(() -> secondRequest);
        assertThat(responseFirst).isEqualTo(expectedResult);
        assertThat(responseSecond).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Item update occurs as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateBook() {
        final String expectedStartResult = "Book";
        final String expectedEndResult = "has update";
        final String request = String.format("%s %s %s %s %s",
                UPDATE_BOOK,  ID_FIRST_BOOK, UPDATE_BOOK_NAME, ID_FIRST_AUTHOR, ID_FIRST_GENRE);
        final String res = (String) shell.evaluate(() -> request);
        assertThat(res)
                .startsWith(expectedStartResult)
                .contains(UPDATE_BOOK_NAME)
                .endsWith(expectedEndResult);
    }
}
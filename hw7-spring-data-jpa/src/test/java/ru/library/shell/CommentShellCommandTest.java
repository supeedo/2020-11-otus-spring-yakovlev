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
class CommentShellCommandTest {

    private static final int COUNT_COMMENTS_IN_BASE = 2;

    private static final String COMMENTS_COUNT = "comments count";
    private static final String COUNT_COMMENTS = "count comments";
    private static final String SHOW_ALL_COMMENTS = "show all comments";
    private static final String ALL_COMMENTS = "all comments";
    private static final String COMMENT_ID = "comment id";
    private static final String ALL_COMM_BY_BOOK = "all comm by book";
    private static final String DELETE_COMMENT = "delete comment";
    private static final String CREATE_COMMENT = "create comment";
    private static final String COMMENT_CREATE = "comments create";
    private static final String UPDATE_COMMENT = "update comment";

    private static final String FIRST_EXPECTED_COMMENT = "test comment 1";
    private static final String SECOND_EXPECTED_COMMENT = "test comment 2";
    private static final String UPDATE_COMMENT_NAME = "UpdateName";
    private static final String ID_FIRST_COMMENT = "1";
    private static final String FIRST_BOOK_ID = "1";

    @Autowired
    private Shell shell;

    @Test
    @DisplayName("Expected quantity is as expected")
    void showCommentsCount() {
        final String expectedResult = String.format("There are %d comments in the library", COUNT_COMMENTS_IN_BASE);
        final String resFirst = (String) shell.evaluate(() -> COMMENTS_COUNT);
        final String resSecond = (String) shell.evaluate(() -> COUNT_COMMENTS);
        assertThat(expectedResult)
                .isEqualTo(resFirst)
                .isEqualTo(resSecond);
    }

    @Test
    @DisplayName("Getting all items as expected")
    void showAllComments() {
        final String resFirst = (String) shell.evaluate(() -> SHOW_ALL_COMMENTS);
        final String resSecond = (String) shell.evaluate(() -> ALL_COMMENTS);
        assertThat(resFirst)
                .contains(FIRST_EXPECTED_COMMENT)
                .contains(SECOND_EXPECTED_COMMENT);
        assertThat(resSecond)
                .contains(FIRST_EXPECTED_COMMENT)
                .contains(SECOND_EXPECTED_COMMENT);
    }

    @Test
    @DisplayName("Getting an item by ID matches what was expected")
    void showCommentById() {
        final String request = String.format("%s %s", COMMENT_ID, ID_FIRST_COMMENT);
        final String response = (String) shell.evaluate(() -> request);
        assertThat(response)
                .contains(ID_FIRST_COMMENT)
                .contains(FIRST_EXPECTED_COMMENT);
    }

    @Test
    @DisplayName("Getting an item by book ID matches what was expected")
    void showAllCommentByBookId() {
        final String request = String.format("%s %s", ALL_COMM_BY_BOOK, FIRST_BOOK_ID);
        final String response = (String) shell.evaluate(() -> request);
        assertThat(response)
                .contains(ID_FIRST_COMMENT)
                .contains(FIRST_EXPECTED_COMMENT);
    }

    @Test
    @DisplayName("Deleting an item by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteCommentById() {
        final String expectedResult = String.format("Comment with id: %s has delete", ID_FIRST_COMMENT);
        final String request = String.format("%s %s", DELETE_COMMENT, ID_FIRST_COMMENT);
        final String response = (String) shell.evaluate(() -> request);
        assertThat(response)
                .isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Adding a new item happens as expected")
    void insertNewComment() {
        final String expectedResult = "Comment has insert";
        final String firstRequest = String.format("%s %s %s", CREATE_COMMENT, UPDATE_COMMENT_NAME, FIRST_BOOK_ID);
        final String secondRequest = String.format("%s %s %s", COMMENT_CREATE, UPDATE_COMMENT_NAME, FIRST_BOOK_ID);
        final String resFirst = (String) shell.evaluate(() -> firstRequest);
        final String resSecond = (String) shell.evaluate(() -> secondRequest);
        assertThat(resFirst).isEqualTo(expectedResult);
        assertThat(resSecond).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Item update occurs as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateComment(){
        final String expectedStartResult = "Comment";
        final String expectedEndResult = "has update";
        final String request = String.format("%s %s %s", UPDATE_COMMENT, ID_FIRST_COMMENT, UPDATE_COMMENT_NAME);
        final String res = (String) shell.evaluate(() -> request);
        assertThat(res)
                .startsWith(expectedStartResult)
                .contains(UPDATE_COMMENT_NAME)
                .endsWith(expectedEndResult);

    }
}
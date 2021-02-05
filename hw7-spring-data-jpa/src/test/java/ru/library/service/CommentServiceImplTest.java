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
class CommentServiceImplTest {

    private static final String EXPECTED_COUNT_COMMENTS_IN_BASE = "There are 2 comments in the library";
    private static final String FIRST_EXPECTED_COMMENT = "test comment 1";
    private static final String SECOND_EXPECTED_COMMENT = "test comment 2";
    private static final String UPDATE_COMMENT = "UpdateComment";
    private static final String ID_FIRST_COMMENT = "1";
    private static final String FIRST_BOOK_ID = "1";

    @Autowired
    private CommentServiceImpl service;

    @Test
    @DisplayName("Expected quantity is as expected")
    void getCount() {
        final String count = service.getCount();
        assertThat(count).isEqualTo(EXPECTED_COUNT_COMMENTS_IN_BASE);
    }

    @Test
    @DisplayName("Getting all items as expected")
    void getAllComments() {
        final String actualMessage = service.getAllComments();
        assertThat(actualMessage)
                .contains(FIRST_EXPECTED_COMMENT)
                .contains(SECOND_EXPECTED_COMMENT);
    }

    @Test
    @DisplayName("Getting an item by ID matches what was expected")
    void getCommentById() {
        final String response = service.getCommentById(Long.parseLong(ID_FIRST_COMMENT));
        assertThat(response)
                .contains(ID_FIRST_COMMENT)
                .contains(FIRST_EXPECTED_COMMENT);
    }

    @Test
    @DisplayName("Getting an item by book ID matches what was expected")
    void getAllCommentsByBookId() {
        final String actualMessage = service.getAllCommentsByBookId(Long.parseLong(FIRST_BOOK_ID));
        assertThat(actualMessage)
                .contains(FIRST_EXPECTED_COMMENT);
    }

    @Test
    @DisplayName("Deleting an item by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteCommentById() {
        final String expectedResult = String.format("Comment with id: %s has delete", ID_FIRST_COMMENT);
        final String response = service.deleteCommentById(Long.parseLong(ID_FIRST_COMMENT));
        assertThat(response)
                .isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Adding a new item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewComment() {
        final String expectedResult = "Comment has insert";
        final String response = service.createNewComment(UPDATE_COMMENT, Long.parseLong(FIRST_BOOK_ID));
        assertThat(response).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Item update occurs as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateComment() {
        final String expectedStartResult = "Comment";
        final String expectedEndResult = "has update";
        final String response = service.updateComment(Long.parseLong(ID_FIRST_COMMENT), UPDATE_COMMENT);
        assertThat(response)
                .startsWith(expectedStartResult)
                .contains(UPDATE_COMMENT)
                .endsWith(expectedEndResult);
    }

    @Test
    @DisplayName("The resulting list is as expected")
    void getTitles() {
        final List<String> expectedTitleList = List.of("id", "Comment", "Book");
        final List<String> actualTitleList = service.getTitles();
        assertThat(expectedTitleList).containsAll(actualTitleList);
    }
}
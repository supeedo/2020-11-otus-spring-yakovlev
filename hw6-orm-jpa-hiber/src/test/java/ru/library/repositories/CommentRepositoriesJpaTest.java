package ru.library.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.library.models.Book;
import ru.library.models.BookComment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(CommentRepositoriesJpa.class)
class CommentRepositoriesJpaTest {

    private static final int EXPECTED_COMMENT_COUNT = 2;
    private static final long FIRST_ID = 1L;
    private static final long SECOND_ID = 2L;
    private static final long NEW_COMMENT_ID = 3L;
    private static final String TEST_COMMENT_TITLE = "Test book title";
    private static final String UPDATE_COMMENT_TITLE = "Update book title";

    @Autowired
    private CommentRepositories commentRep;

    @Autowired
    private TestEntityManager tem;

    @DisplayName("The number of comments is as expected")
    @Test
    void getCommentCount() {
        final long actualCommentCount = commentRep.getCommentCount();
        Assertions.assertThat(actualCommentCount).isEqualTo(EXPECTED_COMMENT_COUNT);
    }

    @DisplayName("The inserted comment is as expected")
    @Test
    void insertComment() {
        final Book book = tem.find(Book.class, FIRST_ID);
        final BookComment commentForInsert = new BookComment(NEW_COMMENT_ID, TEST_COMMENT_TITLE, book);
        commentRep.insertComment(commentForInsert);
        final BookComment actualBook = tem.find(BookComment.class, NEW_COMMENT_ID);
        Assertions.assertThat(actualBook).usingRecursiveComparison().isEqualTo(commentForInsert);
    }

    @DisplayName("Comment with the specified ID removed")
    @Test
    void deleteComment() {
        final BookComment expectedBook = tem.find(BookComment.class, FIRST_ID);
        commentRep.deleteComment(expectedBook);
        assertNull(tem.find(BookComment.class, FIRST_ID));
    }

    @DisplayName("The comment received from the ID corresponds to the expected")
    @Test
    void getCommentById() {
        final BookComment expectedBook = tem.find(BookComment.class, FIRST_ID);
        final BookComment actualBook = commentRep.getCommentById(FIRST_ID).get();
        Assertions.assertThat(expectedBook).usingRecursiveComparison().isEqualTo(actualBook);
    }

    @DisplayName("The resulting list of all comments is as expected")
    @Test
    void getAllComments() {
        final List<BookComment> expectedCommentsList = List.of(
                tem.find(BookComment.class, FIRST_ID),
                tem.find(BookComment.class, SECOND_ID)
        );
        final List<BookComment> actualBooksList = commentRep.getAllComments();
        Assertions.assertThat(expectedCommentsList.get(0)).usingRecursiveComparison()
                .isEqualTo(actualBooksList.get(0));
        Assertions.assertThat(expectedCommentsList.get(1)).usingRecursiveComparison()
                .isEqualTo(actualBooksList.get(1));
    }

    @DisplayName("The resulting list of titles is as expected")
    @Test
    void getTitles() {
        final List<String> expectedTitleBook = List.of("id", "Comment", "Book");
        final List<String> actualTitleBook = commentRep.getTitles();
        Assertions.assertThat(expectedTitleBook).containsExactlyInAnyOrderElementsOf(actualTitleBook);
    }
}
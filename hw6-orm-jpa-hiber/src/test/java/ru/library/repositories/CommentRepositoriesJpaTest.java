package ru.library.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Import(CommentRepositoriesJpa.class)
class CommentRepositoriesJpaTest {
    private static final int EXPECTED_COMMENTS_COUNT = 2;
    @Test
    void getCommentCount() {
    }

    @Test
    void insertComment() {
    }

    @Test
    void updateComment() {
    }

    @Test
    void deleteCommentById() {
    }

    @Test
    void getCommentById() {
    }

    @Test
    void getAllComments() {
    }

    @Test
    void getTitles() {
    }
}
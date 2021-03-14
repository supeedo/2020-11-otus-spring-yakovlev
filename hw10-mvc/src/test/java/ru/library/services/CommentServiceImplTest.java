package ru.library.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import ru.library.Dto.AuthorDto;
import ru.library.Dto.BookDto;
import ru.library.Dto.CommentDto;
import ru.library.Dto.GenreDto;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceImplTest {

    private static final long EXPECTED_COUNT_COMMENTS_IN_BASE = 2L;
    private static final CommentDto FIRST_EXPECTED_COMMENT
            = new CommentDto(1L, "test comment 1", new BookDto(1L, "Clean Code",
            new AuthorDto(1L, "Robert Martin"), new GenreDto(1L, "Computer science")));
    private static final CommentDto SECOND_EXPECTED_COMMENT
            = new CommentDto(2L, "test comment 2", new BookDto(2L, "Effective Java",
            new AuthorDto(2L, "Joshua Bloch"), new GenreDto(1L, "Computer science")));
    private static final String UPDATE_COMMENT = "UpdateComment";
    private static final long ID_FIRST_COMMENT = 1L;
    private static final long FIRST_BOOK_ID = 1L;

    @Autowired
    private CommentServiceImpl service;

    @Transactional(readOnly = true)
    @Test
    @DisplayName("Expected quantity is as expected")
    void getCount() {
        final long count = service.getCount();
        assertThat(count).isEqualTo(EXPECTED_COUNT_COMMENTS_IN_BASE);
    }

    @Transactional(readOnly = true)
    @Test
    @DisplayName("Getting all items as expected")
    void getAllComments() {
        final List<CommentDto> expectedList = new ArrayList<>(Arrays.asList(FIRST_EXPECTED_COMMENT, SECOND_EXPECTED_COMMENT));
        final List<CommentDto> actualList = service.getAllComments();

        assertThat(actualList)
                .isNotEmpty()
                .hasSize((int) EXPECTED_COUNT_COMMENTS_IN_BASE)
                .doesNotHaveDuplicates()
                .containsAll(expectedList);
    }

    @Transactional(readOnly = true)
    @Test
    @DisplayName("Getting an item by ID matches what was expected")
    void getCommentById() {
        final CommentDto actualDto = service.getCommentById(ID_FIRST_COMMENT);
        assertThat(actualDto)
                .isNotNull()
                .isEqualTo(FIRST_EXPECTED_COMMENT)
                .isNotEqualTo(SECOND_EXPECTED_COMMENT);
    }

    @Transactional(readOnly = true)
    @Test
    @DisplayName("Getting items by book id matches what was expected")
    void getAllCommentsByBookId() {
        final List<CommentDto> expectedList = new ArrayList<>(Collections.singletonList(FIRST_EXPECTED_COMMENT));
        final List<CommentDto> actualList = service.getAllCommentsByBookId(FIRST_BOOK_ID);

        assertThat(actualList)
                .isNotEmpty()
                .hasSizeLessThan((int) EXPECTED_COUNT_COMMENTS_IN_BASE)
                .doesNotHaveDuplicates()
                .containsAll(expectedList);
    }

    @Transactional
    @Test
    @DisplayName("Deleting an item by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteCommentById() {
        service.deleteCommentById(ID_FIRST_COMMENT);
        assertThat(service.getAllComments()).hasSizeLessThan((int) EXPECTED_COUNT_COMMENTS_IN_BASE);
        assertThrows(EntityNotFoundException.class, () -> service.getCommentById(ID_FIRST_COMMENT));
    }

    @Transactional
    @Test
    @DisplayName("Adding a new item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewComment() {
        final BookDto bookForComment = new BookDto(1L, "Clean Code",
                new AuthorDto(1L, "Robert Martin"), new GenreDto(1L, "Computer science"));
        final CommentDto commentForCreate = new CommentDto(UPDATE_COMMENT, bookForComment);
        service.createNewComment(commentForCreate);
        assertThat(service.getAllComments()).hasSizeGreaterThan((int) EXPECTED_COUNT_COMMENTS_IN_BASE);
    }

    @Transactional
    @Test
    @DisplayName("Item update occurs as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateComment() {
        CommentDto commentForUpdateDto = service.getCommentById(ID_FIRST_COMMENT);
        commentForUpdateDto.setComment(UPDATE_COMMENT);
        service.updateComment(commentForUpdateDto);
        assertThat(service.getCommentById(ID_FIRST_COMMENT))
                .isNotNull()
                .isEqualTo(commentForUpdateDto)
                .isNotEqualTo(SECOND_EXPECTED_COMMENT);
    }

    @Transactional
    @Test
    @DisplayName("Save item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void save() {
        final BookDto bookForComment = new BookDto(1L, "Clean Code",
                new AuthorDto(1L, "Robert Martin"), new GenreDto(1L, "Computer science"));
        final CommentDto commentForCreate = new CommentDto(UPDATE_COMMENT, bookForComment);
        service.save(commentForCreate);
        assertThat(service.getAllComments()).hasSizeGreaterThan((int) EXPECTED_COUNT_COMMENTS_IN_BASE);
    }
}
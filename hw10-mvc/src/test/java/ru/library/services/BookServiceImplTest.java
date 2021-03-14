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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BookServiceImplTest {
    private static final long EXPECTED_COUNT_BOOKS_IN_BASE = 2L;
    private static final BookDto FIRST_EXPECTED_BOOK = new BookDto(1L, "Clean Code",
            new AuthorDto(1L, "Robert Martin"), new GenreDto(1L, "Computer science"));
    private static final BookDto SECOND_EXPECTED_BOOK = new BookDto(2L, "Effective Java",
            new AuthorDto(2L, "Joshua Bloch"), new GenreDto(1L, "Computer science"));
    private static final String UPDATE_BOOK_NAME = "UpdateName";
    private static final long ID_FIRST_BOOK = 1L;
    private static final long ID_FIRST_AUTHOR = 1L;
    private static final long ID_FIRST_GENRE = 1L;

    @Autowired
    private BookServiceImpl service;

    @Transactional(readOnly = true)
    @Test
    @DisplayName("Getting all items as expected")
    void getBookById() {
        final List<BookDto> expectedList = new ArrayList<>(Arrays.asList(FIRST_EXPECTED_BOOK, SECOND_EXPECTED_BOOK));
        final List<BookDto> actualList = service.getAllBooks();

        assertThat(actualList)
                .isNotEmpty()
                .hasSize((int) EXPECTED_COUNT_BOOKS_IN_BASE)
                .doesNotHaveDuplicates()
                .containsAll(expectedList);
    }

    @Transactional
    @Test
    @DisplayName("Deleting an item by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteBookById() {
        service.deleteBookById(ID_FIRST_BOOK);
        assertThat(service.getAllBooks()).hasSizeLessThan((int) EXPECTED_COUNT_BOOKS_IN_BASE);
        assertThrows(EntityNotFoundException.class, () -> service.getBookById(ID_FIRST_BOOK));
    }

    @Transactional
    @Test
    @DisplayName("Adding a new item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewBook() {
        final BookDto bookForCreate = new BookDto(0L, UPDATE_BOOK_NAME,
                new AuthorDto( "Robert Martin"), new GenreDto( "Computer science"));
        service.createNewBook(bookForCreate);
        assertThat(service.getAllBooks()).hasSizeGreaterThan((int) EXPECTED_COUNT_BOOKS_IN_BASE);
    }

    @Transactional
    @DisplayName("Item update occurs as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void updateBook() {
        BookDto bookForUpdateDto = service.getBookById(ID_FIRST_BOOK);
        bookForUpdateDto.setTitle(UPDATE_BOOK_NAME);
        service.updateBook(bookForUpdateDto);
        assertThat(service.getBookById(ID_FIRST_BOOK))
                .isNotNull()
                .isEqualTo(bookForUpdateDto)
                .isNotEqualTo(SECOND_EXPECTED_BOOK);
    }

    @Transactional
    @Test
    @DisplayName("Save item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void save() {
        final BookDto bookForSave = new BookDto(0L, UPDATE_BOOK_NAME,
                new AuthorDto(1L, "Robert Martin"), new GenreDto(1L, "Computer science"));
        service.save(bookForSave);
        assertThat(service.getAllBooks()).hasSizeGreaterThan((int) EXPECTED_COUNT_BOOKS_IN_BASE);
    }
}
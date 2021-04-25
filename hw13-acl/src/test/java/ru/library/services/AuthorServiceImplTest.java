package ru.library.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.library.dto.AuthorDto;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AuthorServiceImplTest {
    private static final long EXPECTED_COUNT_AUTHORS_IN_BASE = 2L;
    private static final AuthorDto FIRST_EXPECTED_AUTHOR = new AuthorDto(1L, "Robert Martin");
    private static final AuthorDto SECOND_EXPECTED_AUTHOR = new AuthorDto(2L, "Joshua Bloch");
    private static final String UPDATE_AUTHOR_NAME = "UpdateName";
    private static final long ID_FIRST_AUTHOR = 1L;

    @Autowired
    private AuthorServiceImpl service;

    @Test
    @DisplayName("Expected quantity is as expected")
    void getCount() {
        final long count = service.getCount();
        assertThat(count).isEqualTo(EXPECTED_COUNT_AUTHORS_IN_BASE);
    }

    @Test
    @DisplayName("Getting all items as expected")
    void getAllAuthors() {
        final List<AuthorDto> expectedList = new ArrayList<>(Arrays.asList(FIRST_EXPECTED_AUTHOR, SECOND_EXPECTED_AUTHOR));
        final List<AuthorDto> actualList = service.getAllAuthors();

        assertThat(actualList)
                .isNotEmpty()
                .hasSize((int) EXPECTED_COUNT_AUTHORS_IN_BASE)
                .doesNotHaveDuplicates()
                .containsAll(expectedList);
    }

    @Test
    @DisplayName("Getting an item by ID matches what was expected")
    void getAuthorById() {
        final AuthorDto actualDto = service.getAuthorById(ID_FIRST_AUTHOR);
        assertThat(actualDto)
                .isNotNull()
                .isEqualTo(FIRST_EXPECTED_AUTHOR)
                .isNotEqualTo(SECOND_EXPECTED_AUTHOR);
    }

    @Test
    @DisplayName("Deleting an item by id works as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteAuthorById() {
        service.deleteAuthorById(ID_FIRST_AUTHOR);
        assertThat(service.getAllAuthors()).hasSizeLessThan((int) EXPECTED_COUNT_AUTHORS_IN_BASE);
        assertThrows(EntityNotFoundException.class, () -> service.getAuthorById(ID_FIRST_AUTHOR));
    }

    @Test
    @DisplayName("Getting an item by name matches what was expected")
    void getAuthorsByName() {
        final AuthorDto actualDto = service.getAuthorsByName(FIRST_EXPECTED_AUTHOR.getFullName());
        assertThat(actualDto)
                .isNotNull()
                .isEqualTo(FIRST_EXPECTED_AUTHOR)
                .isNotEqualTo(SECOND_EXPECTED_AUTHOR);
    }

    @Test
    @DisplayName("Adding a new item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void createNewAuthor() {
        final AuthorDto authorForCreate = new AuthorDto(UPDATE_AUTHOR_NAME);
        service.createNewAuthor(authorForCreate);
        assertThat(service.getAllAuthors()).hasSizeGreaterThan((int) EXPECTED_COUNT_AUTHORS_IN_BASE);
        assertThat(service.getAuthorsByName(UPDATE_AUTHOR_NAME)).isNotNull();
    }

    @Test
    @DisplayName("Item update occurs as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void updateAuthor() {
        AuthorDto authorForUpdateDto = service.getAuthorById(ID_FIRST_AUTHOR);
        authorForUpdateDto.setFullName(UPDATE_AUTHOR_NAME);
        service.updateAuthor(authorForUpdateDto);
        assertThat(service.getAuthorById(ID_FIRST_AUTHOR))
                .isNotNull()
                .isEqualTo(authorForUpdateDto)
                .isNotEqualTo(SECOND_EXPECTED_AUTHOR);

    }

    @Test
    @DisplayName("Save item happens as expected")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void save() {
        final AuthorDto authorForCreate = new AuthorDto(UPDATE_AUTHOR_NAME);
        service.save(authorForCreate);
        assertThat(service.getAllAuthors()).hasSizeGreaterThan((int) EXPECTED_COUNT_AUTHORS_IN_BASE);
        assertThat(service.getAuthorsByName(UPDATE_AUTHOR_NAME)).isNotNull();
    }
}
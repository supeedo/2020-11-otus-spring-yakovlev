package ru.library.services;


import ru.library.Dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    BookDto getBookById(Long id);

    void deleteBookById(Long id);

    void createNewBook(BookDto bookDTO);

    void updateBook(BookDto bookDTO);

    void save(BookDto book);
}

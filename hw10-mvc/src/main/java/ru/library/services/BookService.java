package ru.library.services;


import ru.library.Dto.BookDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id);

    void deleteBookById(Long id);

    void createNewBook(BookDTO bookDTO);

    void updateBook(BookDTO bookDTO);

    void save(BookDTO book);
}

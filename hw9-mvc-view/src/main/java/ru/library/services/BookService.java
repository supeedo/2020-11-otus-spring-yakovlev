package ru.library.services;


import ru.library.models.Book;

import java.util.List;

public interface BookService {

    String getCount();

    List<Book> getAllBooks();

    String getBookById(Long id);

    String deleteBookById(Long id);

    String createNewBook(String bookName, Long authorId, Long genreId);

    String updateBook(Long id, String bookName, Long authorId, Long genreId);

}

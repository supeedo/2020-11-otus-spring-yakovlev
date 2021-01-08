package ru.library.service;

import ru.library.dto.Book;

import java.util.List;

public interface BookService {

    int getCount();

    String getAllBooks();

    String getBookById(Long id);

    String deleteBookById(Long id);

    String createNewBook(Long id, String bookName, Long authorId, Long genreId);

    String updateBook(Long id, String bookName, Long authorId, Long genreId);

    List<List<String>> prepareForTable(List<Book> books);
}

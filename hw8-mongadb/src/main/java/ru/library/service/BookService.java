package ru.library.service;

import ru.library.models.Book;

import java.util.List;

public interface BookService {

    String getCount();

    String getAllBooks();

    String getBookById(String id);

    String deleteBookById(String id);

    String createNewBook(String bookName, String authorId, String genreId);

    String updateBook(String id, String bookName, String authorId, String genreId);

    List<List<String>> prepareForTable(List<Book> books);
}

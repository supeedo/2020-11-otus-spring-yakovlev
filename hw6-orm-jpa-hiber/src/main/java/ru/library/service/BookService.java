package ru.library.service;

import ru.library.models.Book;

import java.util.List;

public interface BookService {

    long getCount();

    String getAllBooks();

    String getBookById(long id);

    String deleteBookById(long id);

    String createNewBook(String bookName, long authorId, long genreId);

    String updateBook(long id, String bookName, long authorId, long genreId);

    List<List<String>> prepareForTable(List<Book> books);
}

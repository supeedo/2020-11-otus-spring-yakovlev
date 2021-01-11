package ru.library.dao;

import ru.library.dto.Book;

import java.util.List;

public interface BookDao {
    int getBooksCount();
    void insertBook(Book book);
    void updateBook(Book book);
    void deleteBookById(long bookId);
    Book getBookById(long bookId);
    List<Book> getAllBooks();
    List<String> getTitles();
}

package ru.library.dao;

import ru.library.dto.Author;
import ru.library.dto.Book;

import java.util.List;

public interface BookDao {
    int booksCount();
    void insert(Book book);
    void update(Book book);
    void deleteByBookId(long bookId);
    Author getByBookId(long bookId);
    List<Book> getAllBooks();
}

package ru.library.repositories;

import ru.library.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositories {
    Long getBooksCount();
    Book insertBook(Book book);
    void updateBook(Book book);
    void deleteBookById(long bookId);
    Optional<Book> getBookById(long bookId);
    List<Book> getAllBooks();
    List<String> getTitles();
}
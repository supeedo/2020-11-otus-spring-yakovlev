package ru.library.repositories;

import ru.library.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositories {
    long getBooksCount();

    Book insertBook(Book book);

    void updateBook(Book book);

    void deleteBook(Book book);

    Optional<Book> getBookById(long bookId);

    List<Book> getAllBooks();

    List<String> getTitles();
}

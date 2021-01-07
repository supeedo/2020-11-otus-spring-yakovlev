package ru.library.dao;

import org.springframework.stereotype.Repository;
import ru.library.dto.Author;
import ru.library.dto.Book;

import java.util.List;

@Repository
public class BookDaoJdbc implements BookDao {
    @Override
    public int booksCount() {
        return 0;
    }

    @Override
    public void insert(Book book) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void deleteByBookId(long bookId) {

    }

    @Override
    public Author getByBookId(long bookId) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }
}

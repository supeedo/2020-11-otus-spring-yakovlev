package ru.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Book;
import ru.library.repositories.BookRepositories;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    BookRepositories bookRepositories;

    @Autowired
    public BookServiceImpl(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;
    }

    @Transactional(readOnly = true)
    @Override
    public String getCount() {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks(){
       return bookRepositories.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(Long id) {
        return bookRepositories.findById(id).orElseThrow(()->new IllegalArgumentException("Book not found"));
    }

    @Transactional
    @Override
    public void deleteBookById(Long id) {
        bookRepositories.deleteById(id);
    }

    @Transactional
    @Override
    public String createNewBook(String bookName, Long authorId, Long genreId) {
        return null;
    }

    @Transactional
    @Override
    public String updateBook(Long id, String bookName, Long authorId, Long genreId) {
        return null;
    }

    @Transactional
    @Override
    public Book save(Book book) {
        return bookRepositories.save(book);
    }

}

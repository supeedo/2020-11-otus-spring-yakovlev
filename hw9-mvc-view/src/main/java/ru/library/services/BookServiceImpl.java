package ru.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.library.models.Book;
import ru.library.repositories.BookRepositories;

import java.util.List;

@Service
public class BookServiceImpl {
    BookRepositories bookRepositories;

    @Autowired
    public BookServiceImpl(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;
    }

    public List<Book> getAllBooks(){
       return bookRepositories.findAll();
    }
}

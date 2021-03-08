package ru.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.Dto.BookDto;
import ru.library.Dto.BookMapper;
import ru.library.models.Book;
import ru.library.repositories.BookRepositories;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    BookRepositories bookRepositories;

    @Autowired
    public BookServiceImpl(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepositories.findAll();
        return books.stream().map(BookMapper.INSTANCE::bookToBookDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepositories.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return BookMapper.INSTANCE.bookToBookDto(book);
    }

    @Transactional
    @Override
    public void deleteBookById(Long id) {
        bookRepositories.deleteById(id);
    }

    @Transactional
    @Override
    public void createNewBook(BookDto bookDTO) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDTO);
        bookRepositories.save(book);
    }

    @Transactional
    @Override
    public void updateBook(BookDto bookDTO) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDTO);
        bookRepositories.save(book);
    }

    @Transactional
    @Override
    public void save(BookDto bookDto) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
        System.out.println(book.toString());
        bookRepositories.save(book);
    }

}

package ru.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.dto.AuthorMapper;
import ru.library.dto.BookDto;
import ru.library.dto.BookMapper;
import ru.library.dto.GenreMapper;
import ru.library.models.Book;
import ru.library.repositories.BookRepositories;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositories bookRepositories;

    @Autowired
    public BookServiceImpl(BookRepositories bookRepositories) {
        this.bookRepositories = bookRepositories;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAllBooks() {
        final List<Book> books = bookRepositories.findAll();
        return books.stream().map(BookMapper.INSTANCE::bookToBookDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto getBookById(Long id) {
        final Book book = bookRepositories.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
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
        final Book book = BookMapper.INSTANCE.bookDtoToBook(bookDTO);
        bookRepositories.save(book);
    }

    @Transactional
    @Override
    public void updateBook(BookDto bookDTO) {
        Book book = bookRepositories.findById(bookDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(AuthorMapper.INSTANCE.authorDtoToAuthor(bookDTO.getAuthor()));
        book.setGenre(GenreMapper.INSTANCE.genreDtoToGenre(bookDTO.getGenre()));
    }

    @Transactional
    @Override
    public void save(BookDto bookDto) {
        final Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
        bookRepositories.save(book);
    }

}

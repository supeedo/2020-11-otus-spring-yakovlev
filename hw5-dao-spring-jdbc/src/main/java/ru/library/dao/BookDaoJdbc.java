package ru.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.library.dto.Author;
import ru.library.dto.Book;
import ru.library.dto.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int getBooksCount() {
        final String GET_BOOKS_COUNT = "SELECT COUNT(*) FROM BOOKS";
        return Objects.requireNonNull(jdbc.getJdbcOperations().queryForObject(GET_BOOKS_COUNT, Integer.class));
    }

    @Override
    public void insertBook(Book book) {
        final String INSERT_BOOK = "INSERT INTO BOOKS(ID, `BOOK_TITLE`, `AUTHOR_ID`, `GENRE_ID`) VALUES (:id , :bookTitle, :authorId, :genreId)";
        jdbc.update(INSERT_BOOK,
                Map.of("id", book.getId(), "bookTitle", book.getBookTitle(),
                        "authorId", book.getAuthor().getId(),
                        "genreId", book.getGenre().getId()));
    }

    @Override
    public void updateBook(Book book) {
        final String UPDATE_BOOK = "UPDATE BOOKS SET BOOK_TITLE = :bookTitle, AUTHOR_ID=:authorId, GENRE_ID=:genreId WHERE ID=:id";
        jdbc.update(UPDATE_BOOK,
                Map.of("id", book.getId(),
                        "bookTitle", book.getBookTitle(),
                        "authorId", book.getAuthor().getId(),
                        "genreId", book.getGenre().getId()));
    }

    @Override
    public void deleteBookById(long bookId) {
        final String DELETE_BOOK_BY_ID = "DELETE FROM BOOKS WHERE ID = :id";
        jdbc.update(DELETE_BOOK_BY_ID, Map.of("id", bookId));
    }

    @Override
    public Book getBookById(long bookId) {
        final String GET_BOOK_BY_ID = "SELECT BOOKS.ID,  BOOKS.BOOK_TITLE, AUTHORS.ID AS AUTHOR_ID, AUTHORS.FULL_NAME, GENRES.ID as GENRE_ID, GENRES.GENRE_NAME " +
                "FROM BOOKS JOIN AUTHORS ON AUTHOR_ID = AUTHORS.ID JOIN GENRES ON GENRE_ID = GENRES.ID WHERE BOOKS.ID = :id";
        return jdbc.queryForObject(GET_BOOK_BY_ID, Map.of("id", bookId), new BookMapper());
    }

    @Override
    public List<Book> getAllBooks() {
        final String GET_ALL_BOOKS = "SELECT BOOKS.ID,  BOOKS.BOOK_TITLE, AUTHORS.ID AS AUTHOR_ID, AUTHORS.FULL_NAME, GENRES.ID as GENRE_ID, GENRES.GENRE_NAME " +
                " FROM BOOKS JOIN AUTHORS ON AUTHOR_ID = AUTHORS.ID JOIN GENRES ON GENRE_ID = GENRES.ID";
        return jdbc.query(GET_ALL_BOOKS, new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            final long id = rs.getLong("ID");
            final String bookTitle = rs.getString("BOOK_TITLE");
            final Author author = new Author(rs.getLong("AUTHOR_ID"), rs.getString("FULL_NAME"));
            final Genre genre = new Genre(rs.getLong("GENRE_ID"), rs.getString("GENRE_NAME"));
            return new Book(id, bookTitle, author, genre);
        }
    }
}

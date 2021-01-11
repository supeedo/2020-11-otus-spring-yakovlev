package ru.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.library.dto.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    public int getAuthorsCount() {
        final String GET_AUTHORS_COUNT = "SELECT COUNT(*) FROM AUTHORS";
        return Objects.requireNonNull(jdbc.getJdbcOperations().queryForObject(GET_AUTHORS_COUNT, Integer.class));
    }

    @Override
    public void insertAuthor(Author author) {
        final String INSERT_AUTHORS = "INSERT INTO AUTHORS(ID, `FULL_NAME`) VALUES (:id , :fullName)";
        jdbc.update(INSERT_AUTHORS,
                Map.of("id", author.getId(), "fullName", author.getFullName()));
    }

    @Override
    public void updateAuthor(Author author) {
        final String UPDATE_BOOK = "UPDATE AUTHORS SET FULL_NAME = :fullName WHERE ID=:id";
        jdbc.update(UPDATE_BOOK,
                Map.of("id", author.getId(),
                        "fullName", author.getFullName()));
    }

    @Override
    public void deleteAuthorById(long authorId) {
        final String DELETE_BOOK_BY_ID = "DELETE FROM AUTHORS WHERE ID = :id";
        jdbc.update(DELETE_BOOK_BY_ID, Map.of("id", authorId));
    }

    @Override
    public Author getAuthorById(long authorId) {
        final String GET_BOOK_BY_ID = "SELECT ID, FULL_NAME FROM AUTHORS WHERE ID = :id";
        return jdbc.queryForObject(GET_BOOK_BY_ID, Map.of("id", authorId), new AuthorsMapper());
    }

    @Override
    public List<Author> getAllAuthors() {
        final String GET_ALL_BOOKS = "SELECT ID, FULL_NAME FROM AUTHORS";
        return jdbc.query(GET_ALL_BOOKS, new AuthorsMapper());
    }

    @Override
    public List<String> getTitles() {
        return List.of("id", "Author full name");
    }

    private static class AuthorsMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            final long id = rs.getLong("ID");
            final String fullName = rs.getString("FULL_NAME");
            return new Author(id, fullName);
        }
    }
}

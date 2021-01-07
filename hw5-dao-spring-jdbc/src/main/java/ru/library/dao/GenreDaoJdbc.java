package ru.library.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.library.dto.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int getGenreCount() {
        final String GET_GENRES_COUNT = "SELECT COUNT(*) FROM GENRES";
        return Objects.requireNonNull(jdbc.getJdbcOperations().queryForObject(GET_GENRES_COUNT, Integer.class));
    }

    @Override
    public void insertGenre(Genre genre) {
        final String INSERT_GENRE = "INSERT INTO GENRES(ID, `GENRE_NAME`) VALUES (:id , :genreName)";
        jdbc.update(INSERT_GENRE,
                Map.of("id", genre.getID(), "genreName", genre.getGenreName()));
    }

    @Override
    public void updateGenre(Genre genre) {
        final String UPDATE_GENRE = "UPDATE GENRES SET GENRE_NAME = :genreName WHERE ID=:id";
        jdbc.update(UPDATE_GENRE,
                Map.of("id", genre.getID(), "genreName", genre.getGenreName()));
    }

    @Override
    public void deleteGenreById(long genreId) {
        final String DELETE_GENRE_BY_ID = "DELETE FROM GENRES WHERE ID = :id";
        jdbc.update(DELETE_GENRE_BY_ID, Map.of("id", genreId));
    }

    @Override
    public Genre getGenreById(long genreId) {
        final String GET_GENRE_BY_ID = "SELECT ID, GENRE_NAME FROM GENRES WHERE ID = :id";
        return jdbc.queryForObject(GET_GENRE_BY_ID, Map.of("id", genreId), new GenreMapper());
    }

    @Override
    public List<Genre> getAllGenres() {
        final String GET_ALL_GENRES = "SELECT ID,  GENRE_NAME FROM GENRES";
        return jdbc.query(GET_ALL_GENRES, new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            final long id = rs.getLong("ID");
            final String genreName = rs.getString("GENRE_NAME");
            return new Genre(id, genreName);
        }
    }
}

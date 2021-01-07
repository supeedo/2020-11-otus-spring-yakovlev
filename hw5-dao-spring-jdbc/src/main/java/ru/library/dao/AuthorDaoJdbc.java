package ru.library.dao;

import org.springframework.stereotype.Repository;
import ru.library.dto.Author;

import java.util.List;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    @Override
    public int authorsCount() {
        return 0;
    }

    @Override
    public void insert(Author author) {

    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void deleteByAuthorId(long authorId) {

    }

    @Override
    public Author getByAuthorId(long authorId) {
        return null;
    }

    @Override
    public List<Author> getAllAuthors() {
        return null;
    }
}

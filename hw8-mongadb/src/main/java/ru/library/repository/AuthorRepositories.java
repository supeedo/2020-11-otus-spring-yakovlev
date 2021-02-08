package ru.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.library.models.Author;

import java.util.List;

@Repository
public interface AuthorRepositories extends MongoRepository<Author, String> {
    List<Author> findByFullNameContains(String authorName);
}

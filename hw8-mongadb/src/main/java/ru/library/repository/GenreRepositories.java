package ru.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.library.models.Genre;

@Repository
public interface GenreRepositories  extends MongoRepository<Genre, String> {
}

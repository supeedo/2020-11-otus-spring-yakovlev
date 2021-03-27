package ru.library.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.library.domain.Author;


@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String>{
}

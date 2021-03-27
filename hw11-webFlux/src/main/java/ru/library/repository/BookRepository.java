package ru.library.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.library.domain.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}

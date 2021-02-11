package ru.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.library.models.Book;

@Repository
public interface BookRepositories  extends MongoRepository<Book, String> {
}

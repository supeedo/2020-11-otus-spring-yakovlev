package ru.library.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.library.domain.Genre;


@Repository
public interface GenreRepository extends ReactiveMongoRepository<Genre, String>{

    Optional<Genre> findByName(String name);
}

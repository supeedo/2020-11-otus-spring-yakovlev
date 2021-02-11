package ru.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.library.models.Comment;

@Repository
public interface CommentRepositories extends MongoRepository<Comment, String> {
}

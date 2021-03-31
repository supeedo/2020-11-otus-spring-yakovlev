package ru.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.library.models.Comment;

import java.util.List;

@Repository
public interface CommentRepositories extends JpaRepository<Comment, Long> {

    List<Comment> getCommentsByBookId(Long bookId);
}

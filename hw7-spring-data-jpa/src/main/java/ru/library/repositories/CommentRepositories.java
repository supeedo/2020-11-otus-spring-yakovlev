package ru.library.repositories;

import ru.library.models.BookComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositories  extends JpaRepository<BookComment, Long > {
}

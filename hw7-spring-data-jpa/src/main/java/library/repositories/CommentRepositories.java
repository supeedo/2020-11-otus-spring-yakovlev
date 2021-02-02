package library.repositories;

import library.models.BookComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositories  extends JpaRepository<BookComment, Long > {
}

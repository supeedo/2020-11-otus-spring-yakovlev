package library.repositories;

import library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositories extends JpaRepository<Book, Long> {
}

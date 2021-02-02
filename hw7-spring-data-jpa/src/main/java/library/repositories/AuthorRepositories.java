package library.repositories;


import library.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepositories extends JpaRepository<Author, Long > {}

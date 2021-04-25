package ru.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.library.models.User;

import java.util.Optional;

public interface UserRepositories extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);
}

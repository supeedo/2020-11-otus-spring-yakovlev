package ru.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.library.dao.AuthorDao;
import ru.library.dto.Author;
import ru.library.dto.Book;
import ru.library.dto.Genre;

import java.util.stream.Collectors;

@SpringBootApplication
public class Hw5Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Hw5Application.class);
//        try {
//            Console.main(args);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }
}

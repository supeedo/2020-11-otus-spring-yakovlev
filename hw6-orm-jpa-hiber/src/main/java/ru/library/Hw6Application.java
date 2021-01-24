package ru.library;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.logging.ConsoleHandler;

@SpringBootApplication
public class Hw6Application {
    public static void main(String[] args) {
        SpringApplication.run(Hw6Application.class);
//        try {
//            Console.main(args);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }
}

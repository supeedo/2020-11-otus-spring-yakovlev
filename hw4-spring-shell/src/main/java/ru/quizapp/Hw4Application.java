package ru.quizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.quizapp.config.AppConfiguration;
import ru.quizapp.config.LocalizationConfig;

@SpringBootApplication
@EnableConfigurationProperties({AppConfiguration.class, LocalizationConfig.class})
public class Hw4Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw4Application.class, args);
    }
}

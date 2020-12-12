package ru.quizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.quizapp.controller.ExaminationController;

@SpringBootApplication
public class Hw3Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Hw3Application.class, args);
        ExaminationController controller = context.getBean(ExaminationController.class);
        controller.takingAnExamination();
    }

}

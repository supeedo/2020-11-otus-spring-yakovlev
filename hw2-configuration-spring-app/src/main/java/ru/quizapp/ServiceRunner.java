package ru.quizapp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.quizapp.config.AppConfiguration;
import ru.quizapp.controller.ExaminationController;

@ComponentScan
@Configuration
public class ServiceRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ExaminationController controller = context.getBean(ExaminationController.class);
        controller.takingAnExamination();
        context.close();

    }
}

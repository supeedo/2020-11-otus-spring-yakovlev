package ru.quizapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.quizapp.controller.ExaminationController;

public class ServiceRunner {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-config.xml");
        ExaminationController controller = context.getBean(ExaminationController.class);
        controller.outputOfQuestions();
        context.close();

    }
}

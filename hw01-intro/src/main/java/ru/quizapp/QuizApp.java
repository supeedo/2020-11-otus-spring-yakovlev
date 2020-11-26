package ru.quizapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.quizapp.controller.SomeController;

public class QuizApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-config.xml");
        SomeController someController = context.getBean(SomeController.class);
        someController.QuizController();

    }
}

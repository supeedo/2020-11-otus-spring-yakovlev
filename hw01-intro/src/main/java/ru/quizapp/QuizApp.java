package ru.quizapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.quizapp.controller.QuizController;

public class QuizApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-config.xml");
        QuizController controller = context.getBean(QuizController.class);
        controller.outputOfQuestions();
        context.close();

    }
}

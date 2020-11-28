package ru.quizapp.controller;

import ru.quizapp.service.QuestionServiceImpl;
import ru.quizapp.utils.ConsoleHelper;

import java.util.concurrent.atomic.AtomicInteger;


public class QuizControllerImpl implements QuizController {
    private final QuestionServiceImpl questionService;

    public QuizControllerImpl(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    public void outputOfQuestions(){
        AtomicInteger z = new AtomicInteger(1);
        questionService.getQuizInfo().forEach(x->{
            ConsoleHelper.writeMessage(x.getQuestion());
            x.getAnswers().forEach(a-> {
                ConsoleHelper.writeMessage(z + a);
                z.getAndIncrement();
            });
            z.set(1);
        });
    }
}

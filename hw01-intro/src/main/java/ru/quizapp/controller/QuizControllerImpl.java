package ru.quizapp.controller;

import lombok.RequiredArgsConstructor;
import ru.quizapp.service.QuestionService;
import ru.quizapp.utils.ConsoleHelper;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class QuizControllerImpl implements QuizController {
    private final QuestionService questionService;

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

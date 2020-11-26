package ru.quizapp.controller;

import lombok.RequiredArgsConstructor;
import ru.quizapp.service.QuestionService;
import ru.quizapp.utils.ConsoleHelper;

@RequiredArgsConstructor
public class QuizControllerImpl implements QuizController {
    private final QuestionService questionService;

    public void outputOfQuestions(){
        questionService.getQuizInfo().forEach(x->{
            ConsoleHelper.writeMessage(x.getQuestion());
            x.getAnswers().forEach(a-> {
                ConsoleHelper.writeMessage(a);
            });
        });
    }
}

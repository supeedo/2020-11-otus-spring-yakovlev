package ru.quizapp.controller;

import ru.quizapp.service.QuestionService;

public class SomeController {

    public void QuizController(){
        QuestionService questionService = new QuestionService();
        System.out.println(questionService.getQuizInfo());
    }
}

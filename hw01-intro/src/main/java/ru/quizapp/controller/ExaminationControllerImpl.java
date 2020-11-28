package ru.quizapp.controller;

import ru.quizapp.service.ExamTicketServiceImpl;
import ru.quizapp.utils.ConsoleHelper;

import java.util.concurrent.atomic.AtomicInteger;


public class ExaminationControllerImpl implements ExaminationController {
    private final ExamTicketServiceImpl questionService;

    public ExaminationControllerImpl(ExamTicketServiceImpl questionService) {
        this.questionService = questionService;
    }

    public void outputQuestionsAndAnswerOptionsFromTickets() {
        AtomicInteger z = new AtomicInteger(1);
        questionService.getAllTickets().forEach(x -> {
            ConsoleHelper.writeMessage("\n" + x.getQuestion() + "\n");
            x.getAnswers().forEach(a -> {
                ConsoleHelper.writeMessage(z + a);
                z.getAndIncrement();
            });
            z.set(1);
        });
    }
}

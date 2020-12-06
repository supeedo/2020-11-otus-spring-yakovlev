package ru.quizapp.controller;

import ru.quizapp.dto.StudentDTO;
import ru.quizapp.service.ExamTicketService;
import ru.quizapp.utils.ConsoleHelper;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class ExaminationControllerImpl implements ExaminationController {
    private final ExamTicketService questionService;

    public ExaminationControllerImpl(ExamTicketService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void outputQuestionsAndAnswerOptionsFromTickets() {
        try {
            studentRegistration();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public StudentDTO studentRegistration() throws IOException {
        ConsoleHelper.writeMessage("Введите имя");
        String firstName = Objects.requireNonNull(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите фамилию");
        String lastName = Objects.requireNonNull(ConsoleHelper.readString());
        return new StudentDTO(firstName, lastName);
    }
}

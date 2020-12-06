package ru.quizapp.controller;

import ru.quizapp.dto.StudentDTO;
import ru.quizapp.service.ExamTicketService;
import ru.quizapp.utils.ConsoleHelper;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Map;
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
            StudentDTO student = studentRegistration();
            studentTesting(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StudentDTO studentRegistration() throws IOException {
        ConsoleHelper.writeMessage("Введите имя");
        String firstName = Objects.requireNonNull(ConsoleHelper.readString());
        ConsoleHelper.writeMessage("Введите фамилию");
        String lastName = Objects.requireNonNull(ConsoleHelper.readString());
        return new StudentDTO(firstName, lastName);
    }

    private void studentTesting(StudentDTO student) throws IOException {
        AtomicInteger possibleAnswer = new AtomicInteger(1);
        questionService.getAllTickets().forEach(x -> {
            ConsoleHelper.writeMessage("\n" + x.getQuestion() + "\n");

            for (Map.Entry<String, String> qw: x.getAnswers().entrySet()){
                  ConsoleHelper.writeMessage(qw.getKey());
            }


//            x.getAnswers().forEach(a -> {
//                ConsoleHelper.writeMessage(possibleAnswer + a);
//                possibleAnswer.getAndIncrement();
//            });
            try {
                String answer = Objects.requireNonNull(ConsoleHelper.readString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            possibleAnswer.set(1);
        });
    }


}

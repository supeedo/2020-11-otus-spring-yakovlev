package ru.quizapp.controller;

import ru.quizapp.dto.ExaminationDTO;
import ru.quizapp.dto.StudentDTO;
import ru.quizapp.service.ExamTicketService;
import ru.quizapp.utils.ConsoleHelper;

import java.io.IOException;
import java.util.HashMap;
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
            ExaminationDTO examination = studentTesting(student);
            ConsoleHelper.writeMessage(examination.toString());
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

    private int checkAnswer(Map<String, String> mainAnswer, Map<String, String> bufferAnswer, Integer answerNumber) {
        String a = Objects.requireNonNull(mainAnswer.get(bufferAnswer.get(String.valueOf(answerNumber))));
        return Integer.parseInt(a);
    }

    private ExaminationDTO studentTesting(StudentDTO student) throws IOException {
        AtomicInteger examResult = new AtomicInteger();
        AtomicInteger possibleAnswerCount = new AtomicInteger(1);
        // тут задаем вопрос
        questionService.getAllTickets().forEach(x -> {
            ConsoleHelper.writeMessage("\n" + x.getQuestion() + "\n");

            //предоставляем варианты ответа
            Map<String, String> buff = new HashMap<>();
            for (Map.Entry<String, String> qw : x.getAnswers().entrySet()) {
                ConsoleHelper.writeMessage(possibleAnswerCount + qw.getKey());
                buff.put(possibleAnswerCount.toString(), qw.getKey());
                possibleAnswerCount.getAndIncrement();
            }

            // нужно проверить правильность ответа
            try {
                int answerNumber = ConsoleHelper
                                .readOptionAnswerQuestionWithVerification(x.getAnswers().size());
                if(answerNumber == 0){
                    ConsoleHelper.writeMessage("Вы не смогли ввести номер варианта ответа. Вопрос пропускается.");
                    possibleAnswerCount.set(1);
                    return;
                }
                examResult.addAndGet(checkAnswer(x.getAnswers(), buff, answerNumber));
            } catch (IOException e) {
                e.printStackTrace();
            }
            possibleAnswerCount.set(1);
        });

        return new ExaminationDTO(student, examResult.get(), questionService.getAllTickets().size());
    }


}

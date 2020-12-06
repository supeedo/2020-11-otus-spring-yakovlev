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


    private ExaminationDTO studentTesting(StudentDTO student) throws IOException {
        AtomicInteger examResult = new AtomicInteger();
        AtomicInteger possibleAnswerCount = new AtomicInteger(1);
        questionService.getAllTickets().forEach(x -> {
            ConsoleHelper.writeMessage("\n" + x.getQuestion() + "\n");
            Map<String, String> buff = new HashMap<>();
            for (Map.Entry<String, String> qw : x.getAnswers().entrySet()) {
                ConsoleHelper.writeMessage(possibleAnswerCount + qw.getKey());
                buff.put(possibleAnswerCount.toString(), qw.getKey());
                possibleAnswerCount.getAndIncrement();
            }
            try {
                int answerNumber = readOptionAnswerQuestionWithVerificationAndThreeAttempts(x.getAnswers().size());
                examResult.addAndGet(checkAnswer(x.getAnswers(), buff, answerNumber));
            } catch (IOException e) {
                e.printStackTrace();
            }
            possibleAnswerCount.set(1);
        });
        return getTheResultsOfTheExam(student, examResult.get(), questionService.getAllTickets().size());
    }

    private Integer checkAnswer(Map<String, String> mainAnswer, Map<String, String> bufferAnswer, Integer answerNumber) {
        if(answerNumber == 0){
            ConsoleHelper.writeMessage("Вы не смогли ввести номер варианта ответа. Вопрос пропускается.");
            return 0;
        }
        String a = Objects.requireNonNull(mainAnswer.get(bufferAnswer.get(String.valueOf(answerNumber))));
        return Integer.parseInt(a);
    }


    public int readOptionAnswerQuestionWithVerificationAndThreeAttempts(int answerCount) throws IOException {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            String bufferReadString = ConsoleHelper.readString();
            try {
                int buf = Integer.parseInt(bufferReadString);
                if (buf <= answerCount && buf > 0) {
                    result = buf;
                    break;
                }else{
                    ConsoleHelper.writeMessage("Ошибка!!! Такого варианта ответа нет! Поробуйте еще раз.");
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Ошибка!!! Необходимо ввести номер одного из вариантов ответа! Попробуйте еще раз!");
            }
        }
        return result;
    }

    private ExaminationDTO getTheResultsOfTheExam(StudentDTO student, Integer numberOfCorrectAnswers, Integer questionsCount){
        return  new ExaminationDTO(student, numberOfCorrectAnswers, questionsCount);
    }
}

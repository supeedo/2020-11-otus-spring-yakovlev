package ru.quizapp.service;

import ru.quizapp.dto.ExamTicketDTO;
import ru.quizapp.dto.ExaminationDTO;
import ru.quizapp.dto.StudentDTO;
import ru.quizapp.repository.ExamTicketRepository;
import ru.quizapp.utils.ConsoleHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class ExamTicketServiceImpl implements ExamTicketService {
    private final ExamTicketRepository repository;

    public ExamTicketServiceImpl(ExamTicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ExamTicketDTO> getAllTickets() {
        return repository.readAllDataFromDataBase();
    }

    @Override
    public StudentDTO studentRegistration() {
        String firstName = "";
        String lastName = "";
        try {
            ConsoleHelper.writeMessage("Введите имя");
            firstName = Objects.requireNonNull(ConsoleHelper.readString());
            ConsoleHelper.writeMessage("Введите фамилию");
            lastName = Objects.requireNonNull(ConsoleHelper.readString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new StudentDTO(firstName, lastName);
    }

    @Override
    public ExaminationDTO studentTesting(StudentDTO student) {
        AtomicInteger examResult = new AtomicInteger();
        AtomicInteger possibleAnswerCount = new AtomicInteger(1);
        getAllTickets().forEach(x -> {
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
        return getTheResultsOfTheExam(student, examResult.get(), getAllTickets().size());
    }

    private Integer checkAnswer(Map<String, String> mainAnswer, Map<String, String> bufferAnswer, Integer answerNumber) {
        if (answerNumber == 0) {
            ConsoleHelper.writeMessage("Вы не смогли ввести номер варианта ответа. Вопрос пропускается.");
            return 0;
        }
        String a = Objects.requireNonNull(mainAnswer.get(bufferAnswer.get(String.valueOf(answerNumber))));
        return Integer.parseInt(a);
    }


    private int readOptionAnswerQuestionWithVerificationAndThreeAttempts(int answerCount) throws IOException {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            String bufferReadString = ConsoleHelper.readString();
            try {
                int buf = Integer.parseInt(bufferReadString);
                if (buf <= answerCount && buf > 0) {
                    result = buf;
                    break;
                } else {
                    ConsoleHelper.writeMessage("Ошибка!!! Такого варианта ответа нет! Поробуйте еще раз.");
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Ошибка!!! Необходимо ввести номер одного из вариантов ответа! Попробуйте еще раз!");
            }
        }
        return result;
    }

    private ExaminationDTO getTheResultsOfTheExam(StudentDTO student, Integer numberOfCorrectAnswers, Integer questionsCount) {
        return new ExaminationDTO(student, numberOfCorrectAnswers, questionsCount);
    }

}

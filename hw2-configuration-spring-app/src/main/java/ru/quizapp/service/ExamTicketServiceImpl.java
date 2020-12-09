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
    private final ConsoleHelper consoleHelper;

    public ExamTicketServiceImpl(ExamTicketRepository repository, ConsoleHelper consoleHelper) {
        this.repository = repository;
        this.consoleHelper = consoleHelper;
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
            consoleHelper.writeMessage("Введите имя");
            firstName = Objects.requireNonNull(consoleHelper.readString());
            consoleHelper.writeMessage("Введите фамилию");
            lastName = Objects.requireNonNull(consoleHelper.readString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new StudentDTO(firstName, lastName);
    }

    @Override
    public ExaminationDTO studentTesting(StudentDTO student) {
        AtomicInteger examResult = new AtomicInteger();
        AtomicInteger possibleAnswerCount = new AtomicInteger(1);
        List<ExamTicketDTO> examTicketDTOList = getAllTickets();
        examTicketDTOList.forEach(x -> {
            consoleHelper.writeMessage("\n" + x.getQuestion() + "\n");
            Map<String, String> buff = new HashMap<>();
            for (Map.Entry<String, String> qw : x.getAnswers().entrySet()) {
                consoleHelper.writeMessage(possibleAnswerCount + qw.getKey());
                buff.put(possibleAnswerCount.toString(), qw.getKey());
                possibleAnswerCount.getAndIncrement();
            }
            int answerNumber = readOptionAnswerQuestionWithVerificationAndThreeAttempts(x.getAnswers().size());
            examResult.addAndGet(checkAnswer(x.getAnswers(), buff, answerNumber));
            possibleAnswerCount.set(1);
        });
        return getTheResultsOfTheExam(student, examResult.get(), examTicketDTOList.size());
    }

    @Override
    public Integer checkAnswer(Map<String, String> mainAnswer, Map<String, String> bufferAnswer, Integer answerNumber) {
        if (answerNumber == 0) {
            consoleHelper.writeMessage("Вы не смогли ввести номер варианта ответа. Вопрос пропускается.");
            return 0;
        }
        String bufferAnswerNumber = Objects.requireNonNull(mainAnswer.get(bufferAnswer.get(String.valueOf(answerNumber))));
        return Integer.parseInt(bufferAnswerNumber);
    }

    @Override
    public int readOptionAnswerQuestionWithVerificationAndThreeAttempts(int answerCount) {
        int result = 0;
        for (int i = 0; i < 3; i++) {
            String bufferReadString;
            try {
                bufferReadString = consoleHelper.readString();

                int buf = Integer.parseInt(bufferReadString);
                if (buf <= answerCount && buf > 0) {
                    result = buf;
                    break;
                } else {
                    consoleHelper.writeMessage("Ошибка!!! Такого варианта ответа нет! Поробуйте еще раз.");
                }
            } catch (NumberFormatException e) {
                consoleHelper.writeMessage("Ошибка!!! Необходимо ввести номер одного из вариантов ответа! Попробуйте еще раз!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public ExaminationDTO getTheResultsOfTheExam(StudentDTO student, Integer numberOfCorrectAnswers, Integer questionsCount) {
        return new ExaminationDTO(student, numberOfCorrectAnswers, questionsCount);
    }

    @Override
    public void resultsOfTheConductedTesting(ExaminationDTO examination) {
        consoleHelper.writeMessage(examination.toString());
    }
}

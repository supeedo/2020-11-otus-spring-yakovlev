package ru.quizapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.quizapp.dto.ExamTicketDTO;
import ru.quizapp.dto.ExaminationDTO;
import ru.quizapp.dto.StudentDTO;
import ru.quizapp.exceptions.ResourceException;
import ru.quizapp.repository.ExamTicketRepository;
import ru.quizapp.utils.ConsoleHelper;
import ru.quizapp.utils.LocaleDataHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.quizapp.exceptions.ResourceException.ErrorCode.CONSOLE_READING_ERROR;

@Service
public class ExamTicketServiceImpl implements ExamTicketService {
    private static final Logger logger = LoggerFactory.getLogger(ExamTicketService.class);
    private final ExamTicketRepository repository;
    private final ConsoleHelper consoleHelper;
    private final LocaleDataHelper localeDataHelper;

    public ExamTicketServiceImpl(ExamTicketRepository repository, ConsoleHelper consoleHelper, LocaleDataHelper localeMessageHelper) {
        this.repository = repository;
        this.consoleHelper = consoleHelper;
        this.localeDataHelper = localeMessageHelper;
    }

    @Override
    public List<ExamTicketDTO> getAllTickets() {
        return repository.readAllDataFromDataBase();
    }

    @Override
    public StudentDTO studentRegistration() throws ResourceException {
        String firstName, lastName;
        try {
            consoleHelper.writeMessage(localeDataHelper.getLocaleMessage("query.firstname"));
            firstName = Objects.requireNonNull(consoleHelper.readString());
            consoleHelper.writeMessage(localeDataHelper.getLocaleMessage("query.lastname"));
            lastName = Objects.requireNonNull(consoleHelper.readString());
        } catch (IOException error) {
            throw new ResourceException("Error reading responses from student", error, CONSOLE_READING_ERROR);
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
            consoleHelper.writeMessage(localeDataHelper.getLocaleMessage("error.attempts.ended"));
            return 0;
        }
        String bufferAnswerNumber = Objects.requireNonNull(mainAnswer.get(bufferAnswer.get(String.valueOf(answerNumber))));
        return Integer.parseInt(bufferAnswerNumber);
    }

    @Override
    public int readOptionAnswerQuestionWithVerificationAndThreeAttempts(int answerCount) throws ResourceException {
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
                    consoleHelper.writeMessage(localeDataHelper.getLocaleMessage("error.wrong.number"));
                }
            } catch (NumberFormatException e) {
                consoleHelper.writeMessage(localeDataHelper.getLocaleMessage("error.format.answer"));
            } catch (IOException error) {
                throw new ResourceException("Error reading responses from student", error, CONSOLE_READING_ERROR);
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

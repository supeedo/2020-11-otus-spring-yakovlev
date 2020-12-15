package ru.quizapp.service;

import ru.quizapp.dto.ExamTicketDTO;
import ru.quizapp.dto.ExaminationDTO;
import ru.quizapp.dto.StudentDTO;

import java.util.List;
import java.util.Map;

public interface ExamTicketService {
    List<ExamTicketDTO> getAllTickets();

//    StudentDTO studentRegistration();

    ExaminationDTO studentTesting(StudentDTO student);

    Integer checkAnswer(Map<String, String> mainAnswer, Map<String, String> bufferAnswer, Integer answerNumber);

    int readOptionAnswerQuestionWithVerificationAndThreeAttempts(int answerCount);

    ExaminationDTO getTheResultsOfTheExam(StudentDTO student, Integer numberOfCorrectAnswers, Integer questionsCount);

    void resultsOfTheConductedTesting(ExaminationDTO examination);
}

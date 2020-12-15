package ru.quizapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.quizapp.dto.ExaminationDTO;
import ru.quizapp.dto.StudentDTO;
import ru.quizapp.service.ExamTicketService;
import ru.quizapp.service.StudentRegistrationService;

@Service
public class ExaminationControllerImpl implements ExaminationController {
    private static final Logger logger = LoggerFactory.getLogger(ExaminationController.class);
    private final ExamTicketService questionService;
    private final StudentRegistrationService studentRegistration;

    public ExaminationControllerImpl(ExamTicketService questionService, StudentRegistrationService studentRegistration) {
        this.questionService = questionService;
        this.studentRegistration = studentRegistration;
    }

    @Override
    public void takingAnExamination() {
        StudentDTO student = studentRegistration.studentRegistration();
        logger.info("Enrolled student = " + student);
        ExaminationDTO examination = questionService.studentTesting(student);
        logger.info("Exam passed with results = " + examination);
        questionService.resultsOfTheConductedTesting(examination);
    }
}

package ru.quizapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.quizapp.dto.ExaminationDTO;
import ru.quizapp.dto.StudentDTO;
import ru.quizapp.service.ExamTicketService;

@Controller
public class ExaminationControllerImpl implements ExaminationController {
    private static final Logger logger = LoggerFactory.getLogger(ExaminationController.class);
    private final ExamTicketService questionService;

    public ExaminationControllerImpl(ExamTicketService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void takingAnExamination() {
        StudentDTO student = questionService.studentRegistration();
        logger.info("Enrolled student = " + student);
        ExaminationDTO examination = questionService.studentTesting(student);
        logger.info("Exam passed with results = " + examination);
        questionService.resultsOfTheConductedTesting(examination);
    }
}

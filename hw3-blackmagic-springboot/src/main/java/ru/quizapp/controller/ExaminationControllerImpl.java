package ru.quizapp.controller;

import org.springframework.stereotype.Controller;
import ru.quizapp.dto.ExaminationDTO;
import ru.quizapp.dto.StudentDTO;
import ru.quizapp.service.ExamTicketService;

@Controller
public class ExaminationControllerImpl implements ExaminationController {
    private final ExamTicketService questionService;

    public ExaminationControllerImpl(ExamTicketService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void takingAnExamination() {
        StudentDTO student = questionService.studentRegistration();
        ExaminationDTO examination = questionService.studentTesting(student);
        questionService.resultsOfTheConductedTesting(examination);
    }


}

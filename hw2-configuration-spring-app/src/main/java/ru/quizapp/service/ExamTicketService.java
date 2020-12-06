package ru.quizapp.service;

import ru.quizapp.dto.ExamTicketDTO;
import ru.quizapp.dto.ExaminationDTO;
import ru.quizapp.dto.StudentDTO;

import java.util.List;

public interface ExamTicketService {
    List<ExamTicketDTO> getAllTickets();

    StudentDTO studentRegistration();

    ExaminationDTO studentTesting(StudentDTO student);
}

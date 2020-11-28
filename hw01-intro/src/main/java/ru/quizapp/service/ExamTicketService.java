package ru.quizapp.service;

import ru.quizapp.dto.ExamTicketDTO;

import java.util.List;

public interface ExamTicketService {
    List<ExamTicketDTO> getQuizInfo();
}

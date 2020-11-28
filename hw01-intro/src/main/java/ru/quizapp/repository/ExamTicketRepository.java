package ru.quizapp.repository;

import ru.quizapp.dto.ExamTicketDTO;

import java.util.List;

public interface ExamTicketRepository {
    List<ExamTicketDTO> readAllDataFromBase();
}

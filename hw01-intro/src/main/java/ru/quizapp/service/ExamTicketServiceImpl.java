package ru.quizapp.service;

import ru.quizapp.dto.ExamTicketDTO;
import ru.quizapp.repository.ExamTicketRepository;

import java.util.List;


public class ExamTicketServiceImpl implements ExamTicketService {
    private final ExamTicketRepository repository;

    public ExamTicketServiceImpl(ExamTicketRepository repository) {
        this.repository = repository;
    }

    public List<ExamTicketDTO> getQuizInfo(){
      return repository.readAllDataFromBase();
  }

}

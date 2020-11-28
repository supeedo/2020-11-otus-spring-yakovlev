package ru.quizapp.service;

import ru.quizapp.dto.QuestionDTO;
import ru.quizapp.repository.QuestionsRepository;

import java.util.List;


public class QuestionServiceImpl implements QuestionService {
    private final QuestionsRepository repository;

    public QuestionServiceImpl(QuestionsRepository repository) {
        this.repository = repository;
    }

    public List<QuestionDTO> getQuizInfo(){
      return repository.readAllDataFromBase();
  }

}

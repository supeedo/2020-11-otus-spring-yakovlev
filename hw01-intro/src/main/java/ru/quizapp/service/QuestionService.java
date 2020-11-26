package ru.quizapp.service;

import lombok.RequiredArgsConstructor;
import ru.quizapp.dto.QuestionDTO;
import ru.quizapp.repository.QuestionsRepository;

import java.util.List;

@RequiredArgsConstructor
public class QuestionService {
    private final QuestionsRepository repository;

  public List<QuestionDTO> getQuizInfo(){
      return repository.readData();
  }

}

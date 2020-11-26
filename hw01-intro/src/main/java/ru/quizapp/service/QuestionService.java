package ru.quizapp.service;

import lombok.RequiredArgsConstructor;
import ru.quizapp.dto.QuestionDTO;
import ru.quizapp.repository.QuestionsRepositoryImpl;

import java.util.List;

@RequiredArgsConstructor
public class QuestionService {
    private final QuestionsRepositoryImpl repository;

  public List<QuestionDTO> getQuizInfo(){
      return repository.readAllDataFromBase();
  }

}

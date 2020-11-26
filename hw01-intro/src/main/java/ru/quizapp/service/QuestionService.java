package ru.quizapp.service;

import ru.quizapp.dto.QuestionDTO;
import ru.quizapp.repository.SomeRepository;

public class QuestionService {

  public QuestionDTO getQuizInfo(){
      return new SomeRepository().readData();
  }

}

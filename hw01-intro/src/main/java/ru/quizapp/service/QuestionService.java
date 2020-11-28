package ru.quizapp.service;

import ru.quizapp.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {
    List<QuestionDTO> getQuizInfo();
}

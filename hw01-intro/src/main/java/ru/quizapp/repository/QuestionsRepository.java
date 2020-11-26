package ru.quizapp.repository;

import ru.quizapp.dto.QuestionDTO;

import java.util.List;

public interface QuestionsRepository {
    List<QuestionDTO> readAllDataFromBase();
}

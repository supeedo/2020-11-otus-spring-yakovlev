package ru.quizapp.repository;

import ru.quizapp.dto.QuestionDTO;

public class SomeRepository {

    public QuestionDTO readData(){
        // создаем объект ДТО, его инициализируем параметрами из файла
        QuestionDTO dto = new QuestionDTO();
        dto.setQuestion("Some questions");
        return dto;
    }
}

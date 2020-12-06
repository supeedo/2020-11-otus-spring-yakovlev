package ru.quizapp.dto;

import java.util.UUID;

public class ExaminationDTO {
    private final String ID = UUID.randomUUID().toString();
    private final StudentDTO student;
    private final Integer rightAnswers;
    private final Integer totalQuestions;

    public ExaminationDTO(StudentDTO student, Integer rightAnswers, Integer totalQuestions) {
        this.student = student;
        this.rightAnswers = rightAnswers;
        this.totalQuestions = totalQuestions;
    }

    @Override
    public String toString() {
        return "Экзамен №" +
                ":" + ID + '\'' +
                ",\n " + student +
                ",\n Правильных ответов = " + rightAnswers +
                " из " + totalQuestions;
    }
}

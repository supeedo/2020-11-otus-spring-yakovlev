package ru.quizapp.dto;

import java.time.LocalDate;
import java.util.UUID;

public class ExaminationDTO {
    private final String ID = UUID.randomUUID().toString();
    private final String date;
    private final StudentDTO student;
    private final Integer rightAnswers;
    private final Integer totalQuestions;

    public ExaminationDTO(StudentDTO student, Integer rightAnswers, Integer totalQuestions) {
        this.student = student;
        this.rightAnswers = rightAnswers;
        this.totalQuestions = totalQuestions;
        this.date = LocalDate.now().toString();
    }

    @Override
    public String toString() {
        return "Экзамен №:" +
                " " + ID + ",\n" +
                " от " + date +
                ",\n " + student +
                ",\n Правильных ответов = " + rightAnswers +
                " из " + totalQuestions;
    }

    public String getID() {
        return ID;
    }

    public String getDate() {
        return date;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public Integer getRightAnswers() {
        return rightAnswers;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }
}

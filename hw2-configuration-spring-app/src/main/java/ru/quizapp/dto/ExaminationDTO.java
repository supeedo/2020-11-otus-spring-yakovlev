package ru.quizapp.dto;

import java.util.UUID;

public class ExaminationDTO {
    private final String ID = UUID.randomUUID().toString();
    private final StudentDTO student;
    private final int examResult;

    public ExaminationDTO(StudentDTO student, int examResult) {
        this.student = student;
        this.examResult = examResult;
    }
}

package ru.quizapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class QuestionDTO {
    private String question;
    private Set<String> answers = new HashSet<>();
}

package ru.quizapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class QuestionDTO {
    private String question;
    private Set<String> answers;
}

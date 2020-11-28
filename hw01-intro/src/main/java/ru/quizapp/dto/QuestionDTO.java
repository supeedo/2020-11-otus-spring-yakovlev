package ru.quizapp.dto;

import java.util.Set;


public class QuestionDTO {
    private String question;
    private Set<String> answers;

    public String getQuestion() {
        return question;
    }

    public QuestionDTO setQuestion(String question) {
        this.question = question;
        return this;
    }

    public Set<String> getAnswers() {
        return answers;
    }

    public QuestionDTO setAnswers(Set<String> answers) {
        this.answers = answers;
        return this;
    }
}

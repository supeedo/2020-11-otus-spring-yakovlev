package ru.quizapp.dto;

import java.util.Set;


public class ExamTicketDTO {
    private String question;
    private Set<String> answers;

    public String getQuestion() {
        return question;
    }

    public ExamTicketDTO setQuestion(String question) {
        this.question = question;
        return this;
    }

    public Set<String> getAnswers() {
        return answers;
    }

    public ExamTicketDTO setAnswers(Set<String> answers) {
        this.answers = answers;
        return this;
    }
}

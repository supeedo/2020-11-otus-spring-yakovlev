package ru.quizapp.dto;

import java.util.Set;


public class ExamTicketDTO {
    private final String question;
    private final Set<String> answers;

    private ExamTicketDTO(ExamTicketDTOBuilder builder) {
        this.question = builder.question;
        this.answers = builder.answers;
    }

    public String getQuestion() {
        return question;
    }

    public Set<String> getAnswers() {
        return answers;
    }

    public static class ExamTicketDTOBuilder{
        private  String question;
        private  Set<String> answers;

        public ExamTicketDTOBuilder setQuestion(String question) {
            this.question = question;
            return this;
        }

        public ExamTicketDTOBuilder setAnswers(Set<String> answers) {
            this.answers = answers;
            return this;
        }

        public ExamTicketDTO build(){
            return new ExamTicketDTO(this);
        }
    }

}

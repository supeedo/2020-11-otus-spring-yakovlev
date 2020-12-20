package ru.quizapp.dto;

import java.util.Map;


public class ExamTicketDTO {
    private final String question;
    private final Map<String, String> answers;

    private ExamTicketDTO(ExamTicketDTOBuilder builder) {
        this.question = builder.question;
        this.answers = builder.answers;
    }

    public String getQuestion() {
        return question;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public static class ExamTicketDTOBuilder{
        private  String question;
        private  Map<String, String> answers;

        public ExamTicketDTOBuilder setQuestion(String question) {
            this.question = question;
            return this;
        }

        public ExamTicketDTOBuilder setAnswers(Map<String, String> answers) {
            this.answers = answers;
            return this;
        }

        public ExamTicketDTO build(){
            return new ExamTicketDTO(this);
        }
    }

}

package ru.quizapp.dto;

public class StudentDTO {

    private final String firstName;
    private final String lastName;

    public StudentDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Студент: " +
                firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

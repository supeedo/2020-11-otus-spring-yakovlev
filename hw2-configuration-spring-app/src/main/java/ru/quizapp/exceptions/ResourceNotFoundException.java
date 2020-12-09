package ru.quizapp.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String errorMessage, Throwable err) {
            super(errorMessage, err);
        }
}

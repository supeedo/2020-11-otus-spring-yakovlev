package ru.quizapp.service;

import ru.quizapp.dto.StudentDTO;
import ru.quizapp.exceptions.ResourceException;

public interface StudentRegistrationService {
    StudentDTO studentRegistration() throws ResourceException;
}

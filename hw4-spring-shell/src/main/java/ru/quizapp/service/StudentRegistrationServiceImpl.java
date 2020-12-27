package ru.quizapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.quizapp.dto.StudentDTO;
import ru.quizapp.exceptions.ResourceException;
import ru.quizapp.utils.LocaleDataHelper;

import java.util.Objects;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {
    private static final Logger logger = LoggerFactory.getLogger(StudentRegistrationServiceImpl.class);
    private final IOServiceImpl consoleHelper;
    private final LocaleDataHelper localeDataHelper;

    public StudentRegistrationServiceImpl(IOServiceImpl consoleHelper, LocaleDataHelper localeDataHelper) {
        this.consoleHelper = consoleHelper;
        this.localeDataHelper = localeDataHelper;
    }


    @Override
    public StudentDTO studentRegistration() throws ResourceException {
        String firstName;
        String lastName;
        consoleHelper.writeMessage(localeDataHelper.getLocaleMessage("query.firstname"));
        firstName = Objects.requireNonNull(consoleHelper.readString());
        logger.info("Got the student's firstname = " + firstName);
        consoleHelper.writeMessage(localeDataHelper.getLocaleMessage("query.lastname"));
        lastName = Objects.requireNonNull(consoleHelper.readString());
        logger.info("Got the student's lastname = " + lastName);
        return new StudentDTO(firstName, lastName);
    }
}

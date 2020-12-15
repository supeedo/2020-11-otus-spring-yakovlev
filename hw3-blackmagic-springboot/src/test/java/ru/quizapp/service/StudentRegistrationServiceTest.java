package ru.quizapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.quizapp.dto.StudentDTO;

@SpringBootTest
class StudentRegistrationServiceTest {

    private static final String testFirstName = "test-First-Name-Student";
    private static final String testLastName = "test-Last-Name-Student";

    @MockBean
    private IOServiceImpl consoleHelper;

    @Autowired
    StudentRegistrationService service;

    @Test
    @DisplayName("Check receipt studentDTO")
    public void studentRegistration() {
        Mockito.when(consoleHelper.readString()).thenReturn(testFirstName, testLastName);
        StudentDTO studentDTO = service.studentRegistration();
        Assertions.assertNotNull(studentDTO, "Object is null");
        Assertions.assertEquals(studentDTO.getFirstName(), testFirstName, "FirstName various");
        Assertions.assertEquals(studentDTO.getLastName(), testLastName, "LastName various");
    }

}
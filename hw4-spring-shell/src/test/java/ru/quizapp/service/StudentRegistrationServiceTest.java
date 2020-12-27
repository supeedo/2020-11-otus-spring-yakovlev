package ru.quizapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.quizapp.dto.StudentDTO;
import ru.quizapp.utils.LocaleDataHelper;

@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
class StudentRegistrationServiceTest {

    private static final String testFirstName = "test-First-Name-Student";
    private static final String testLastName = "test-Last-Name-Student";

    @MockBean
    private IOServiceImpl consoleHelper;
    @MockBean
    private LocaleDataHelper localeDataHelper;

    @Autowired
    private StudentRegistrationService service;

    @Test
    @DisplayName("Check receipt studentDTO")
    void studentRegistration() {
        Mockito.when(consoleHelper.readString()).thenReturn(testFirstName, testLastName);
        StudentDTO studentDTO = service.studentRegistration();
        Assertions.assertNotNull(studentDTO, "Object is null");
        Assertions.assertEquals(testFirstName, studentDTO.getFirstName(), "FirstName various");
        Assertions.assertEquals(testLastName, studentDTO.getLastName(), "LastName various");
    }

}
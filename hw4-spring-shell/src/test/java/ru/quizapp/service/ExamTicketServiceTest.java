package ru.quizapp.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.quizapp.dto.ExamTicketDTO;
import ru.quizapp.dto.ExaminationDTO;
import ru.quizapp.dto.StudentDTO;
import ru.quizapp.repository.ExamTicketRepository;
import ru.quizapp.utils.LocaleDataHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
class ExamTicketServiceTest {
    private static final String testFirstName = "test-First-Name-Student";
    private static final String testLastName = "test-Last-Name-Student";

    private final static List<ExamTicketDTO> questionDTOList = new ArrayList<>();

    @MockBean
    private ExamTicketRepository repository;

    @MockBean
    private IOServiceImpl consoleHelper;

    @Autowired
    private ExamTicketService service;

    private StudentDTO student;

    @BeforeEach
    public void setUp() {
        student = new StudentDTO(testFirstName, testLastName);
        questionDTOList.add(new ExamTicketDTO.ExamTicketDTOBuilder()
                .setQuestion("Tests mock questions")
                .setAnswers(new HashMap<>() {{
                    put(" Test mock answers", "1");
                }}).build()
        );
        Mockito.when(repository.getDataFromCsvFile())
                .thenReturn(questionDTOList);
    }


    @Test
    @DisplayName("Compare examination ticket ")
    public void getAllTickets() {
        ExamTicketDTO ticketDTOFromBase = repository.getDataFromCsvFile().get(0);
        ExamTicketDTO ticketDTOFromService = service.getAllTickets().get(0);
        Assertions.assertEquals(
                ticketDTOFromBase,
                ticketDTOFromService, "exam tickets are not equal");

    }


    @Test
    public void studentTesting() {
        Mockito.when(consoleHelper.readString()).thenReturn("1");
        ExaminationDTO dummyExam = new ExaminationDTO(student, 1, 1);
        ExaminationDTO examinationDTO = this.service.studentTesting(student);
        Assertions.assertNotNull(examinationDTO, "Object is null");
    }

    public void checkAnswer() {
    }

}
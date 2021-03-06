package ru.quizapp.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.quizapp.dto.ExamTicketDTO;
import ru.quizapp.dto.ExaminationDTO;
import ru.quizapp.dto.StudentDTO;
import ru.quizapp.exceptions.ResourceException;
import ru.quizapp.repository.ExamTicketRepository;
import ru.quizapp.utils.ConsoleHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ru.quizapp.exceptions.ResourceException.ErrorCode.CONSOLE_READING_ERROR;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
class ExamTicketServiceTest {
    private final String testFirstName = "testFirstNameStudent";
    private final String testLastName = "testLastNameStudent";

    private final static List<ExamTicketDTO> questionDTOList = new ArrayList<>();

    @Mock
    private ExamTicketRepository repository;

    @Mock
    private ConsoleHelper consoleHelper;

    private ExamTicketService service;

    private StudentDTO student;

    @BeforeEach
    public void setUp() {
        consoleHelper = Mockito.mock(ConsoleHelper.class);
        this.service = new ExamTicketServiceImpl(repository, consoleHelper);
        questionDTOList.add(new ExamTicketDTO.ExamTicketDTOBuilder()
                .setQuestion("Tests mock questions")
                .setAnswers(new HashMap<>() {{
                    put(" Test mock answers", "1");
                }}).build()
        );
        Mockito.when(repository.readAllDataFromDataBase())
                .thenReturn(questionDTOList);
        student = new StudentDTO(testFirstName, testLastName);
    }


    @Test
    @DisplayName("Compare examination ticket ")
    public void getAllTickets() {
        ExamTicketDTO ticketDTOFromBase = repository.readAllDataFromDataBase().get(0);
        ExamTicketDTO ticketDTOFromService = service.getAllTickets().get(0);
        Assertions.assertEquals(
                ticketDTOFromBase,
                ticketDTOFromService, "exam tickets are not equal");

    }

    @Test
    @DisplayName("Check receipt studentDTO")
    public void studentRegistration() {
        try {
            Mockito.when(consoleHelper.readString()).thenReturn(testFirstName, testLastName);
            StudentDTO studentDTO = service.studentRegistration();
            Assertions.assertNotNull(studentDTO);
            Assertions.assertEquals(studentDTO.getFirstName(), testFirstName, "FirstName разные");
            Assertions.assertEquals(studentDTO.getLastName(), testLastName, "LastName разные");
        } catch (IOException error) {
            throw new ResourceException("Error reading data from console", error, CONSOLE_READING_ERROR);
        }
    }

    @Test
    public void studentTesting() {
        try {
            Mockito.when(consoleHelper.readString()).thenReturn("1");
            ExaminationDTO dummyExam = new ExaminationDTO(student, 1, 1);
            ExaminationDTO examinationDTO = this.service.studentTesting(student);
            Assertions.assertNotNull(examinationDTO);
        } catch (IOException error) {
            throw new ResourceException("Error reading data from console", error, CONSOLE_READING_ERROR);
        }
    }

    public void checkAnswer() {
    }

}
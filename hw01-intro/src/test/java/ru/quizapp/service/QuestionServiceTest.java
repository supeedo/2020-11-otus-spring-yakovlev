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
import ru.quizapp.repository.ExamTicketRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
class QuestionServiceTest {

    private static List<ExamTicketDTO> questionDTOList = new ArrayList<>();

    @Mock
    private ExamTicketRepository repository;

    private ExamTicketService service;


    @BeforeEach
    void setUp() {
        this.service = new ExamTicketServiceImpl(repository);

        this.questionDTOList.add(new ExamTicketDTO()
                .setQuestion("Tests mock questions")
                .setAnswers(new HashSet<>() {{
                    add("Test mock answers");
                }}));
        Mockito.when(repository.readAllDataFromDataBase())
                .thenReturn(questionDTOList);
    }


    @Test
    @DisplayName("compare ticket list")
    void getQuizInfo() {
        Assertions.assertEquals(
                service.getAllTickets(),
                repository.readAllDataFromDataBase(), "Список билетов не равен");

    }


}
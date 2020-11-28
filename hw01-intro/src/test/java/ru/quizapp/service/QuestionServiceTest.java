package ru.quizapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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

    List<ExamTicketDTO> questionDTOList = new ArrayList<>();

    @Mock private ExamTicketRepository repository;

    private ExamTicketService service;



    @BeforeEach
    void setUp() {
        this.service = new ExamTicketServiceImpl(repository);

        questionDTOList.add(new ExamTicketDTO()
                .setQuestion("test Mock questions")
                .setAnswers(new HashSet<>() {{
                    add("test Mock Answer");
                }}));

    }


    @Test
    void getQuizInfo() {
    }




}
package ru.quizapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.quizapp.dto.QuestionDTO;
import ru.quizapp.repository.QuestionsRepository;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {
    private static final List<QuestionDTO> questionDTOList = new ArrayList<>();

    @Mock
    private QuestionsRepository repository;

    private QuestionServiceImpl service;



    @BeforeEach
    void setUp() {
        service = new QuestionServiceImpl(repository);
//
//        questionDTOList.add(new QuestionDTO()
//                .setQuestion("test Mock questions")
//                .setAnswers(new HashSet<>() {{
//                    add("test Mock Answer");
//                }}));

    }


    @Test
    void getQuizInfo() {
    }




}
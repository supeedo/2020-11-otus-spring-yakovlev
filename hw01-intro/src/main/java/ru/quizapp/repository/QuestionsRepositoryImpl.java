package ru.quizapp.repository;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.quizapp.dto.QuestionDTO;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
public class QuestionsRepositoryImpl implements QuestionsRepository {

    private final String dataLink;

    public List<QuestionDTO> readAllDataFromBase() {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        try (Reader in = new FileReader(getClass().getClassLoader().getResource(dataLink).getPath())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {
                questionDTOList.add(new QuestionDTO()
                        .setQuestion(record.get(0))
                        .setAnswers(new HashSet<>() {{
                            add(record.get(1));
                            add(record.get(2));
                            add(record.get(3));
                        }}));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionDTOList;
    }
}

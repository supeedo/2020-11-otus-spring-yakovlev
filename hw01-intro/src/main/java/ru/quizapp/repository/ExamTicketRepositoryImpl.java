package ru.quizapp.repository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.quizapp.dto.ExamTicketDTO;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ExamTicketRepositoryImpl implements ExamTicketRepository {

    private final String dataLink;

    public ExamTicketRepositoryImpl(String dataLink) {
        this.dataLink = dataLink;
    }

    @Override
    public List<ExamTicketDTO> readAllDataFromDataBase() {
        List<ExamTicketDTO> questionDTOList = new ArrayList<>();
        try (Reader in = new FileReader(getClass().getClassLoader().getResource(dataLink).getPath())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {
                questionDTOList.add(new ExamTicketDTO()
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

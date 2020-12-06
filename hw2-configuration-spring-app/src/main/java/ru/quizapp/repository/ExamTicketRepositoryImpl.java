package ru.quizapp.repository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.quizapp.dto.ExamTicketDTO;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;


public class ExamTicketRepositoryImpl implements ExamTicketRepository {

    private final String dataLink;

    public ExamTicketRepositoryImpl(String dataLink) {
        this.dataLink = dataLink;
    }

    @Override
    public List<ExamTicketDTO> readAllDataFromDataBase() {
        List<ExamTicketDTO> questionDTOList = new ArrayList<>();
        try (Reader in = new FileReader(getAbsolutePathToDataFile(dataLink))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records)
                questionDTOList.add(new ExamTicketDTO.ExamTicketDTOBuilder()
                        .setQuestion(record.get(0))
                        .setAnswers(new HashMap<>() {{
                            put(record.get(1), record.get(2));
                            put(record.get(3), record.get(4));
                            put(record.get(5), record.get(6));
                        }}).build()
                );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionDTOList;
    }

    private String getAbsolutePathToDataFile(String dataLink){
        return Objects.requireNonNull(this.getClass().getClassLoader().getResource(dataLink)).getPath();
    }
}

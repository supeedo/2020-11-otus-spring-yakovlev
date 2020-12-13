package ru.quizapp.repository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.quizapp.config.AppConfiguration;
import ru.quizapp.dto.ExamTicketDTO;
import ru.quizapp.exceptions.ResourceException;
import ru.quizapp.utils.LocaleDataHelper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static ru.quizapp.exceptions.ResourceException.ErrorCode.READING_FROM_DATABASE_ERROR;

@Component
public class ExamTicketRepositoryImpl implements ExamTicketRepository {
    private static final Logger logger = LoggerFactory.getLogger(ExamTicketRepository.class);
    private final LocaleDataHelper localeDataHelper;
    private final String dataLink;

    public ExamTicketRepositoryImpl(LocaleDataHelper dataHelper) {
        this.localeDataHelper = dataHelper;
        this.dataLink = localeDataHelper.getLocaleMessage("db.url");
    }

    @Override
    public List<ExamTicketDTO> readAllDataFromDataBase() throws ResourceException {
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
        } catch (IOException error) {
            throw new ResourceException("Error reading data from resource", error, READING_FROM_DATABASE_ERROR);
        }
        return questionDTOList;
    }

    private String getAbsolutePathToDataFile(String dataLink) {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResource(dataLink)).getPath();
    }
}

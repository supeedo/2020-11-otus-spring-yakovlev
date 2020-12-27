package ru.quizapp.repository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.quizapp.dto.ExamTicketDTO;
import ru.quizapp.exceptions.ResourceException;
import ru.quizapp.utils.LocaleDataHelper;
import ru.quizapp.utils.parser.DataParser;
import ru.quizapp.utils.parser.DataParserImpl;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

import static ru.quizapp.exceptions.ResourceException.ErrorCode.READING_FROM_DATABASE_ERROR;

@Component
public class ExamTicketRepositoryImpl implements ExamTicketRepository {
    private static final Logger logger = LoggerFactory.getLogger(ExamTicketRepositoryImpl.class);
    private final LocaleDataHelper localeDataHelper;
    private final String dataLink;
    private final DataParser dataParser;

    public ExamTicketRepositoryImpl(LocaleDataHelper dataHelper, DataParserImpl dataParser) {
        this.localeDataHelper = dataHelper;
        this.dataParser = dataParser;
        this.dataLink = localeDataHelper.getLocaleMessage("db.url");
    }

    @Override
    public List<ExamTicketDTO> getDataFromCsvFile() throws ResourceException {
        List<ExamTicketDTO> questionDTOList;
        try (Reader in = new FileReader(getAbsolutePathToDataFile(dataLink))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            logger.info("Information received from the database = {}", records);
            questionDTOList = dataParser.parseDataInListTicket(records);
        } catch (IOException error) {
            throw new ResourceException("Error reading data from resource", error, READING_FROM_DATABASE_ERROR);
        }
        logger.info("Test list generated = {}", questionDTOList);
        return questionDTOList;
    }

    private String getAbsolutePathToDataFile(String dataLink) {
        logger.info("Attempt to form full link to the data file = {}", dataLink);
        return Objects.requireNonNull(this.getClass().getClassLoader().getResource(dataLink)).getPath();
    }


}

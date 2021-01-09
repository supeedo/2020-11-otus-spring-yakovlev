package ru.quizapp.utils.parser;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.quizapp.dto.ExamTicketDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DataParserImpl implements DataParser{
    private static final Logger logger = LoggerFactory.getLogger(DataParserImpl.class);

    @Override
    public List<ExamTicketDTO> parseDataInListTicket(Iterable<CSVRecord> records) {
        logger.info("Parse from database and create tickets");
        List<ExamTicketDTO> questionDTOList = new ArrayList<>();
        for (CSVRecord record : records)
            questionDTOList.add(new ExamTicketDTO.ExamTicketDTOBuilder()
                    .setQuestion(record.get(0))
                    .setAnswers(
                    Map.of(record.get(1), record.get(2), record.get(3), record.get(4), record.get(5), record.get(6))
                    ).build()
            );
        logger.debug("Parse ended = {}", questionDTOList);
        return questionDTOList;
    }
}

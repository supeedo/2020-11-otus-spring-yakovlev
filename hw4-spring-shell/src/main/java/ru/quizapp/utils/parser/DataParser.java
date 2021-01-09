package ru.quizapp.utils.parser;

import org.apache.commons.csv.CSVRecord;
import ru.quizapp.dto.ExamTicketDTO;

import java.util.List;

public interface DataParser {
    List<ExamTicketDTO> parseDataInListTicket(Iterable<CSVRecord> records);
}

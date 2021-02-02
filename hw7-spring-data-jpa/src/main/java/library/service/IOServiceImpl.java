package library.service;

import library.exceptions.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static library.exceptions.ResourceException.ErrorCode.CONSOLE_READING_ERROR;

public class IOServiceImpl implements IOService {

    private static final Logger logger = LoggerFactory.getLogger(IOServiceImpl.class);
    private final BufferedReader reader;
    private final PrintStream out;

    public IOServiceImpl() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.out = System.out;
    }


    @Override
    public void writeMessage(String message) {
        logger.info("Write in console the message = {}", message);
        out.println(message);
    }

    @Override
    public String readString() throws ResourceException {
        logger.info("Read from console");
        String readString;
        try {
            readString = reader.readLine();
        } catch (IOException e) {
            throw new ResourceException("Error reading data from console", e, CONSOLE_READING_ERROR);
        }
        return readString;
    }
}

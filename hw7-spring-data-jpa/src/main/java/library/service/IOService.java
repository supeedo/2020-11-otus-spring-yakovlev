package library.service;


import library.exceptions.ResourceException;

public interface IOService {
    void writeMessage(String message);

    String readString() throws ResourceException;
}

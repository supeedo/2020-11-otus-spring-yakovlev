package ru.library.service;

import ru.library.exceptions.ResourceException;

public interface IOService {
    void writeMessage(String message);
    String readString()throws ResourceException;
}

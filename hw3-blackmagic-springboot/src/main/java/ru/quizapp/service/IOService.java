package ru.quizapp.service;

import ru.quizapp.exceptions.ResourceException;


public interface IOService {
    void writeMessage(String message);
    String readString()throws ResourceException;
}

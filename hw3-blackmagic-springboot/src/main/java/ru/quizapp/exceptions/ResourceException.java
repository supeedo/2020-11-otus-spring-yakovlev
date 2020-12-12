package ru.quizapp.exceptions;

public class ResourceException extends RuntimeException {
    private final ErrorCode code;

    public ResourceException(ErrorCode code) {
        super();
        this.code = code;
    }

    public ResourceException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }

    public ResourceException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public ResourceException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return this.code;
    }

    public enum ErrorCode {
        READING_FROM_DATABASE_ERROR,
        CONSOLE_READING_ERROR
    }
}

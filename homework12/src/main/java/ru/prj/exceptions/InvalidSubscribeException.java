package ru.prj.exceptions;

public class InvalidSubscribeException extends RuntimeException {
    public InvalidSubscribeException(final String message) {
        super(message);
    }
}

package ru.prj.exceptions;

public class StudentNotExistException extends RuntimeException {

    public StudentNotExistException(final String message ) {
        super(message);
    }
}

package ru.prj.exceptions;

public class InvalidCreationStudentException extends RuntimeException {
    public InvalidCreationStudentException(final String message) {
        super(message);
    }
}

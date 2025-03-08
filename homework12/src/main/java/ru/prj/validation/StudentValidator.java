package ru.prj.validation;


import ru.prj.exceptions.InvalidCreationStudentException;
import ru.prj.exceptions.InvalidSubscribeException;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class StudentValidator {
    private StudentValidator() {
    }

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static void validateCreation(String name, String email) throws InvalidCreationStudentException {
        if (name == null || name.isEmpty())
            throw new InvalidCreationStudentException("Name cannot be empty");

        if (email == null || email.isEmpty())
            throw new InvalidCreationStudentException("Email cannot be empty");
        if (!isValidEmail(email))
            throw new InvalidCreationStudentException("Invalid email");
    }

    public static void validateSubscribe(List<String> list, Map<Integer, String> map) throws InvalidSubscribeException {
        if (list == null || list.isEmpty())
            return;

        boolean allContained = map.values().containsAll(list);

        if (!allContained)
            throw new InvalidSubscribeException("You have not subscribed to right course");
    }

    private static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(email).matches();
    }
}

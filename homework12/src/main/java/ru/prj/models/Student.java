package ru.prj.models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public record Student(Long id, String fullName, String email, List<String> courses) {
    public Student(String fullName, String email) {
        this(ThreadLocalRandom.current().nextLong(), fullName, email, new ArrayList<>());
    }

    public Student(Long id, String fullName, String email) {
        this(id, fullName, email, new ArrayList<>());
    }
}

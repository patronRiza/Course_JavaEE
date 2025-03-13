package ru.prj.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Student {
    private Long id;
    private String fullName;
    private String email;
    private List<String> courses;


    public Student(String fullName, String email) {
        this.id = ThreadLocalRandom.current().nextLong();
        this.fullName = fullName;
        this.email = email;
        this.courses = new ArrayList<>();
    }

    public Student(Long id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.courses = new ArrayList<>();
    }

    public Student(Long id, String fullName, String email, List<String> courses) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.courses = courses;
    }
}

package ru.prj.repositories;

import ru.prj.models.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> all();
    Student findById(Long id);
    Student create(String fullName, String email);
    Student subscribe(Long id, List<String> courses);
}

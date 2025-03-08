package ru.prj.repositories;


import ru.prj.exceptions.StudentNotExistException;
import ru.prj.models.Student;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StudentRepository {

    private final Map<Long, Student> allStudents = new ConcurrentHashMap<>();

    public Map<Long, Student> getAllStudents() {
        return Collections.unmodifiableMap(allStudents);
    }

    public Student create(String fullName, String email) {
        Student newStudent = new Student(fullName, email);
        allStudents.putIfAbsent(newStudent.id(), newStudent);
        return allStudents.get(newStudent.id());
    }

    public Student subscribe(Long id, List<String> courses) {
        if (!allStudents.containsKey(id)) {
            throw new StudentNotExistException("Student not exist");
        }
        allStudents.get(id).courses().clear();
        allStudents.get(id).courses().addAll(courses);
        return allStudents.get(id);
    }
}

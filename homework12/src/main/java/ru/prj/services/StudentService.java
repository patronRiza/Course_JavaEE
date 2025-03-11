package ru.prj.services;

import ru.prj.models.Student;
import ru.prj.repositories.StudentRepository;

import java.util.List;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents()
                .values()
                .stream()
                .toList();
    }

    public Student createStudent(String fullName, String email) {
        return studentRepository.create(fullName, email);
    }

    public Student subscribeStudent(Long id, List<String> courses) {
        return studentRepository.subscribe(id, courses);
    }
}

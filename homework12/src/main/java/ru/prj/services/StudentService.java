package ru.prj.services;


import ru.prj.models.Student;
import ru.prj.repositories.StudentRepository;
import ru.prj.repositories.implementations.CourseRepository;
import ru.prj.utils.StudentValidator;

import java.util.List;
import java.util.Map;

public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.all();
    }

    public Student createStudent(String fullName, String email) {
        StudentValidator.validateCreation(fullName, email);
        return studentRepository.create(fullName, email);
    }

    public Student subscribeStudent(Long id, List<String> courses) {
        StudentValidator.validateSubscribe(courses, courseRepository.getCourses());
        return studentRepository.subscribe(id, courses);
    }

    public Map<Integer, String> getAllCourses() {
        return courseRepository.getCourses();
    }

}

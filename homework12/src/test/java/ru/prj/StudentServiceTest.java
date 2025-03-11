package ru.prj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.prj.models.Student;
import ru.prj.repositories.StudentRepository;
import ru.prj.services.StudentService;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet() {
        Student student = new Student(1L, "John Doe", "john@example.com");
        when(studentRepository.getAllStudents()).thenReturn(Map.of(student.id(), student));

        List<Student> students = studentService.getAllStudents();

        assertEquals(1, students.size());
        assertEquals(student.id(), students.getFirst().id());
        verify(studentRepository).getAllStudents();
    }


    @Test
    void doPost() {
        Student student = new Student("Jane Doe", "jane@example.com");
        when(studentRepository.create("Jane Doe", "jane@example.com")).thenReturn(student);

        Student createdStudent = studentService.createStudent("Jane Doe", "jane@example.com");

        assertNotNull(createdStudent);
        assertEquals("Jane Doe", createdStudent.fullName());
        verify(studentRepository).create("Jane Doe", "jane@example.com");
    }

    @Test
    void doPut() {
        Student student = new Student("Mark Smith", "mark@example.com");
        when(studentRepository.subscribe(student.id(), List.of("Java", "C#"))).thenReturn(student);

        Student updatedStudent = studentService.subscribeStudent(student.id(), List.of("Java", "C#"));

        assertNotNull(updatedStudent);
        verify(studentRepository).subscribe(student.id(), List.of("Java", "C#"));
    }
}

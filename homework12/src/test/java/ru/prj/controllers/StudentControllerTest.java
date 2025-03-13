package ru.prj.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.prj.exceptions.InvalidCreationStudentException;
import ru.prj.exceptions.InvalidSubscribeException;
import ru.prj.exceptions.StudentNotExistException;
import ru.prj.models.Student;
import ru.prj.models.dto.ErrorModel;
import ru.prj.models.dto.StudentAndCoursesResponse;
import ru.prj.models.dto.StudentRequest;
import ru.prj.models.dto.SubscribeRequest;
import ru.prj.services.StudentService;
import ru.prj.services.UserSessionService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @Mock
    private UserSessionService userSessionService;

    @InjectMocks
    private StudentController studentController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
         closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Тест получения всех студентов")
    void getAllStudentsAndCourses() {
        Map<Integer, String> courses = Map.of(1, "Java");
        List<Student> students = List.of(new Student(1L, "John", "john@mail.ru", courses.values().stream().toList()));
        when(studentService.getAllStudents()).thenReturn(students);
        when(studentService.getAllCourses()).thenReturn(courses);

        StudentAndCoursesResponse response = studentController.getAllStudents();

        assertEquals(courses, response.courses());
        assertEquals(students, response.students());
        verify(studentService).getAllStudents();
    }

    @Test
    @DisplayName("Тест регистрации студента")
    void registerStudent() {
        StudentRequest request = new StudentRequest("John", "john@mail.ru");
        when(userSessionService.getFullName()).thenReturn("John");
        when(userSessionService.getEmail()).thenReturn("john@mail.ru");

        ResponseEntity<?> response = studentController.registerStudent(request, Collections.emptyList());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Data has been registered. Let to choose courses", response.getBody());
    }

    @Test
    @DisplayName("Тест завершении регистрации студента")
    void completeRegistration() {
        SubscribeRequest subscribeRequest = new SubscribeRequest(null, List.of("PHP"));
        Student student = new Student(1L, "John", "john@mail.ru");
        when(userSessionService.getFullName()).thenReturn("John");
        when(userSessionService.getEmail()).thenReturn("john@mail.ru");
        when(studentService.createStudent("John", "john@mail.ru")).thenReturn(student);
        when(studentService.subscribeStudent(1L, List.of("PHP"))).thenReturn(student);

        ResponseEntity<?> response = studentController.completeRegistration(subscribeRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
    }

    @Test
    @DisplayName("Тест отсутствие студента")
    void handleStudentNotExistException() {
        StudentNotExistException exception = new StudentNotExistException("Student not exist");

        ResponseEntity<?> response = studentController.handleStudentNotExistException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(new ErrorModel("Student not exist"), response.getBody());
    }

    @Test
    @DisplayName("Тест неверные данные при регистрации студента")
    void handleInvalidCreationStudentException() {
        InvalidCreationStudentException exception = new InvalidCreationStudentException("Invalid student creation");

        ResponseEntity<?> response = studentController.handleInvalidCreationStudentException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(new ErrorModel("Invalid student creation"), response.getBody());
    }

    @Test
    @DisplayName("Тест неверные данные при подписке студента на курсы")
    void handleInvalidSubscribeException() {
        InvalidSubscribeException exception = new InvalidSubscribeException("Invalid subscription");

        ResponseEntity<?> response = studentController.handleInvalidSubscribeException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(new ErrorModel("Invalid subscription"), response.getBody());
    }

    @Test
    @DisplayName("Тест неизвестной ошибки")
    void handleException() {
        Exception exception = new Exception("Invalid subscription");

        ResponseEntity<?> response = studentController.handleException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(new ErrorModel("Invalid subscription"), response.getBody());
    }


}
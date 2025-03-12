package ru.prj.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    public final StudentService studentService;
    public final UserSessionService session;

    public StudentController(StudentService studentService, UserSessionService session) {
        this.studentService = studentService;
        this.session = session;
        log.info("StudentController initialized");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentAndCoursesResponse getAllStudents() {
        return new StudentAndCoursesResponse(studentService.getAllCourses(), studentService.getAllStudents());
    }

    @GetMapping(value = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Integer, String> getAllCourses() {
        return studentService.getAllCourses();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(
            @RequestBody StudentRequest studentRequest,
            @RequestParam(name = "courses", required = false) List<String> courses
    ) {
        session.saveStudentData(studentRequest.fullName(), studentRequest.email());

        if (courses != null && !courses.isEmpty()) {
            return completeRegistration(new SubscribeRequest(null, courses));
        }
        log.info("Data has been registered. Let to choose courses");
        return ResponseEntity.ok("Data has been registered. Let to choose courses");
    }

    @PostMapping("/complete-registration")
    public ResponseEntity<?> completeRegistration(@RequestBody SubscribeRequest request) {

        String fullName = session.getFullName();
        String email = session.getEmail();

        if (fullName == null || email == null) {
            throw new IllegalArgumentException("Full name and email are required");
        }

        var student = studentService.createStudent(fullName, email);
        var subscribedStudent = studentService.subscribeStudent(student.getId(), request.courses());
        session.invalidate();
        return ResponseEntity.ok().body(subscribedStudent);
    }

    @PostMapping
    public Student createStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.createStudent(studentRequest.fullName(), studentRequest.email());
    }

    @PutMapping
    public Student subscribeStudent(@RequestBody SubscribeRequest request) {
        return studentService.subscribeStudent(request.id(), request.courses());
    }

    @ExceptionHandler(StudentNotExistException.class)
    public ResponseEntity<ErrorModel> handleEntryNotExistException(final StudentNotExistException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorModel(e.getMessage()));
    }

    @ExceptionHandler(InvalidCreationStudentException.class)
    public ResponseEntity<ErrorModel> handleEntryNotExistException(final InvalidCreationStudentException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorModel(e.getMessage()));
    }

    @ExceptionHandler(InvalidSubscribeException.class)
    public ResponseEntity<ErrorModel> handleEntryNotExistException(final InvalidSubscribeException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorModel(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> handleEntryNotExistException(final Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorModel(e.getMessage()));
    }
}
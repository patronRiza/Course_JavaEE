package ru.prj;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.prj.models.Student;
import ru.prj.services.StudentService;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private StudentService studentService;


    private StudentServlet studentServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentServlet = new StudentServlet(studentService);
    }

    @Test
    void testDoGet() throws IOException, ServletException {
        Student student = new Student("Jane Doe", "jane@example.com");
        when(studentService.getAllStudents()).thenReturn(List.of(student));

        StringWriter responseWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(printWriter);

        studentServlet.doGet(request, response);

        printWriter.flush();
        String responseString = responseWriter.toString();

        assertTrue(responseString.contains("Jane Doe"));
        assertTrue(responseString.contains("jane@example.com"));
        verify(studentService).getAllStudents();
    }

    @Test
    void doPost() throws IOException, ServletException {
        String requestBody = "{\"fullName\": \"Jane Doe\", \"email\": \"jane@example.com\"}";
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(requestBody)));

        Student student = new Student("Jane Doe", "jane@example.com");
        when(studentService.createStudent("Jane Doe", "jane@example.com")).thenReturn(student);

        StringWriter responseWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(printWriter);

        studentServlet.doPost(request, response);
        printWriter.flush();

        verify(studentService).createStudent("Jane Doe", "jane@example.com");
    }

    @Test
    void doPut() throws IOException, ServletException {
        Student student = new Student("Mark Smith", "mark@example.com");
        when(studentService.subscribeStudent(student.id(), List.of("Java", "C#"))).thenReturn(student);

        String requestBody = String.format(
                "{\"id\": \"%s\", \"selectedCourses\": [\"Java\", \"C#\"]}",
                String.valueOf(student.id())
        );
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(requestBody)));


        StringWriter responseWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(printWriter);

        studentServlet.doPut(request, response);
        printWriter.flush();

        verify(studentService).subscribeStudent(student.id(), List.of("Java", "C#"));
    }
}

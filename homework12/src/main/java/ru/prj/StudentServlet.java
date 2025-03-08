package ru.prj;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.prj.exceptions.InvalidCreationStudentException;
import ru.prj.exceptions.InvalidSubscribeException;
import ru.prj.exceptions.StudentNotExistException;
import ru.prj.models.ErrorModel;
import ru.prj.models.Student;
import ru.prj.repositories.StudentRepository;
import ru.prj.services.StudentService;
import ru.prj.validation.StudentValidator;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class StudentServlet extends HttpServlet {

    private static final String DEFAULT_TYPE = "application/json";
    private static final Logger logger = LoggerFactory.getLogger(StudentServlet.class);

    protected static final Map<Integer, String> courses = new TreeMap<>(
            Map.of(
                    1, "Java",
                    2, "C#",
                    3, "Python",
                    4, "PHP")
    );

    public final transient StudentService studentService;

    public StudentServlet() {
        this.studentService = new StudentService(new StudentRepository());
        logger.info("StudentServlet initialized");
        logger.debug("Debug log to verify configuration.");
    }

    public StudentServlet(final StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(DEFAULT_TYPE);
        PrintWriter out = resp.getWriter();

        Gson gson = new Gson();
        String jsonCourses = gson.toJson(courses);
        out.println("{ \"courses\": " + jsonCourses + ", ");

        List<Student> students = studentService.getAllStudents();
        String jsonResponse = gson.toJson(students);

        out.print("\"students\": " + jsonResponse + "}");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = req.getReader().readLine()) != null) {
                sb.append(line);
            }

            Gson gson = new Gson();
            Student studentFromRequest = gson.fromJson(sb.toString(), Student.class);

            String name = studentFromRequest.fullName();
            String email = studentFromRequest.email();

            StudentValidator.validateCreation(name, email);

            Student student = studentService.createStudent(name, email);
            logger.info("Student with ID {} has been created", student.id());

            doGet(req, resp);
        } catch (InvalidCreationStudentException e) {
            handleUserException(e, resp);
        } catch (Exception e) {
            handleServerError(e, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = req.getReader().readLine()) != null) {
                sb.append(line);
            }

            Gson gson = new Gson();
            Map<String, Object> requestBody = gson.fromJson(sb.toString(), Map.class);
            Long id = Long.valueOf(requestBody.get("id").toString());
            Type listType = new TypeToken<List<String>>() {
            }.getType();
            List<String> selectedCourses = gson.fromJson(gson.toJson(requestBody.get("selectedCourses")), listType);

            StudentValidator.validateSubscribe(selectedCourses, courses);

            Student student = studentService.subscribeStudent(id, selectedCourses);

            logger.info("Student with ID {} has been subscribed to courses {}", student.id(), student.courses());

            doGet(req, resp);
        } catch (InvalidSubscribeException | StudentNotExistException e) {
            handleUserException(e, resp);
        } catch (Exception e) {
            handleServerError(e, resp);
        }
    }

    private void handleServerError(Exception e, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        resp.setContentType(DEFAULT_TYPE);
        ErrorModel errorModel = new ErrorModel("Some error from server... " + e.getMessage());
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        logger.info(e.getMessage());
        out.print(gson.toJson(errorModel));
        out.flush();
    }

    private void handleUserException(Exception e, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.setContentType(DEFAULT_TYPE);
        ErrorModel errorModel = new ErrorModel(e.getMessage());
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        logger.info(e.getMessage());
        out.print(gson.toJson(errorModel));
        out.flush();
    }
}
package ru.prj.repositories.implementations;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.prj.exceptions.StudentNotExistException;
import ru.prj.models.Student;
import ru.prj.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.prj.utils.Constant.*;

public class StudentRepositoryImpl implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> all() {
        return jdbcTemplate.query(GET_ALL_STUDENTS, studentRowMapper);
    }

    @Override
    public Student findById(Long id) {
        List<Student> students = jdbcTemplate.query(GET_STUDENT_BY_ID, new Object[]{id}, studentRowMapper);
        if (students.isEmpty())
            throw new StudentNotExistException("Student not exist");
        else
            return students.getFirst();
    }

    @Override
    public Student create(String fullName, String email) {
        Student newStudent = new Student(fullName, email);
        jdbcTemplate.update(CREATE_STUDENT, studentParams(newStudent));
        return findById(newStudent.getId());
    }

    @Override
    public Student subscribe(Long id, List<String> courses) throws StudentNotExistException {
        var student = findById(id);
        student.setCourses(courses);
        jdbcTemplate.update(SUBSCRIBE_STUDENT, String.join(",", student.getCourses()), student.getId());
        return student;
    }

    private static final RowMapper<Student> studentRowMapper = (rs, rowNum) ->
    {
        String courses = rs.getString("courses");
        List<String> coursesList = (courses == null || courses.isBlank())
                ? new ArrayList<>()
                : Arrays.stream(courses.split(","))
                .map(String::trim)
                .toList();

        return new Student(rs.getLong("id"),
                rs.getString("full_name"),
                rs.getString("email"),
                coursesList
        );
    };

    private Object[] studentParams(Student student) {
        return new Object[]{
                student.getId(),
                student.getFullName(),
                student.getEmail(),
                String.join(",", student.getCourses())
        };
    }
}

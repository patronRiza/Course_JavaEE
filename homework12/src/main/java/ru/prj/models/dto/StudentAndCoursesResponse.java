package ru.prj.models.dto;

import ru.prj.models.Student;

import java.util.List;
import java.util.Map;

public record StudentAndCoursesResponse(Map<Integer, String> courses, List<Student> students) {
}

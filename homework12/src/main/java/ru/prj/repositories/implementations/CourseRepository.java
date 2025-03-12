package ru.prj.repositories.implementations;

import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
public class CourseRepository {
    private final Map<Integer, String> courses = new TreeMap<>(
            Map.of(
                    1, "Java",
                    2, "C#",
                    3, "Python",
                    4, "PHP")
    );
}

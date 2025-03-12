package ru.prj.utils;

public class Constant {
    public static final String CREATE_STUDENT = """
            insert into students(id, full_name, email, courses)
            values(?,?,?,?)
            """;
    public static final String GET_ALL_STUDENTS = "select * from students";
    public static final String GET_STUDENT_BY_ID = "select * from students WHERE id=?";
    public static final String UPDATE_STUDENT = "update students set full_name=?, email=?, courses=? where id=?";
    public static final String SUBSCRIBE_STUDENT = "update students set courses=? where id=?";
    public static final String CHECK_STUDENT = "select * from students where id=?";

    private Constant() {
    }
}

package ru.prj.configs;

public class Constant {

    public static final String CREATE_NOTE = """
            insert into notes(date_of_update, title, content)
            values(?,?,?)
            """;
    public static final String GET_ALL_NOTES = "select * from notes";
    public static final String UPDATE_NOTE = "update notes set date_of_update=CURRENT_TIMESTAMP, title=?, content=? where id=?";
    public static final String DELETE_NOTE = "delete from notes where id=?";

    private Constant() {
    }
}

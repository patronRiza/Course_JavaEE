package ru.prj.repositories;

import ru.prj.models.Note;

import java.util.List;

public interface INoteRepo {

    String CREATE_NOTE = """
            insert into notes(date_of_update, title, content)
            values(?,?,?)
            """;
    String GET_ALL_NOTES = "select * from notes";
    String UPDATE_NOTE = "update notes set date_of_update=CURRENT_TIMESTAMP, title=?, content=?  where id=?";
    String DELETE_NOTE = "delete from notes where id=?";

    void createNote(Note note);
    List<Note> getNotes();
    void updateNote(String title, String content, Long id);
    void deleteNote(Long id);
}

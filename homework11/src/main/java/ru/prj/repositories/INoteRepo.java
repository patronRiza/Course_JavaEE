package ru.prj.repositories;

import ru.prj.models.Note;

import java.util.List;

public interface INoteRepo {
    void createNote(Note note);
    List<Note> getNotes();
    void updateNote(String title, String content, Long id);
    void deleteNote(Long id);
}

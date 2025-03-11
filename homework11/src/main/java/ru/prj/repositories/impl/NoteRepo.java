package ru.prj.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.prj.configs.IJDBCTemplateConfig;
import ru.prj.models.Note;
import ru.prj.repositories.INoteRepo;

import java.util.List;

import static ru.prj.configs.Constant.*;

@Repository
public class NoteRepo implements INoteRepo {

    private final JdbcTemplate jdbcTemplate;

    public NoteRepo(final IJDBCTemplateConfig jdbcTemplateConfig) {
        this.jdbcTemplate = jdbcTemplateConfig.jdbcTemplate();
    }

    @Override
    public void createNote(Note note) {
        jdbcTemplate.update(CREATE_NOTE, notesParams(note));
    }

    @Override
    public List<Note> getNotes() {
        return jdbcTemplate.query(GET_ALL_NOTES, noteRowMapper);
    }

    @Override
    public void updateNote(String title, String content, Long id) {
        jdbcTemplate.update(UPDATE_NOTE, title, content, id);
    }

    @Override
    public void deleteNote(Long id) {
        jdbcTemplate.update(DELETE_NOTE, id);
    }

    private static final RowMapper<Note> noteRowMapper = (rs, rowNum) ->
        new Note(rs.getLong("id"),
                rs.getTimestamp("date_of_update").toLocalDateTime(),
                rs.getString("title"),
                rs.getString("content")
        );

    private Object[] notesParams(Note note) {
        return new Object[] {
                note.dateTime(),
                note.title(),
                note.content()
        };
    }

    public static RowMapper<Note> getNoteRowMapper() {
        return noteRowMapper;
    }
}

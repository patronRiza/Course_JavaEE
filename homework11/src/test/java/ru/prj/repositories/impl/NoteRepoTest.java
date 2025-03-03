package ru.prj.repositories.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import ru.prj.configs.LocalJDBCTemplateConfig;
import ru.prj.models.Note;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({NoteRepo.class, LocalJDBCTemplateConfig.class})
@ActiveProfiles("sandbox")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NoteRepoTest {

    private static Note note;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NoteRepo noteRepo;

    @BeforeAll
    static void init() {
        note = new Note(1L, LocalDateTime.now(), "TestTitle", "TestContent");
    }

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("alter sequence notes_id_seq restart with 1");
        jdbcTemplate.execute("delete from notes");
        jdbcTemplate.execute("COMMIT");
    }

    @AfterAll
    static void tearDown(@Autowired JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("drop schema public cascade");
        jdbcTemplate.execute("create schema public");
        jdbcTemplate.execute("grant all on schema public to public ");
    }


    @Test
    @DisplayName("Test of repository - creating note")
    @Order(1)
    void createNote() {
        noteRepo.createNote(note);

        var notesAfterCreate = jdbcTemplate.query(
                "select * from notes where id=?",
                NoteRepo.getNoteRowMapper(),
                note.id()
        );

        assertEquals(1, notesAfterCreate.size());
        assertEquals(note.title(), notesAfterCreate.get(0).title());
        assertEquals(note.content(), notesAfterCreate.get(0).content());
    }

    @Test
    @DisplayName("Test of repository - get notes")
    @Order(2)
    void getNotes() {
        jdbcTemplate.update(
                "insert into notes(date_of_update, title, content) values(?,?,?)",
                note.dateTime(), note.title(), note.content()
        );
        jdbcTemplate.execute("COMMIT");

        var notes = noteRepo.getNotes();

        assertEquals(1, notes.size());
        assertEquals(note.title(), notes.get(0).title());
        assertEquals(note.content(), notes.get(0).content());
    }

    @Test
    @DisplayName("Test of repository - update note")
    @Order(4)
    void updateNote() {
        Long insertedId  = jdbcTemplate.queryForObject(
                "insert into notes(date_of_update, title, content) values(?,?,?) returning id",
                Long.class,
                note.dateTime(), note.title(), note.content()
        );
        jdbcTemplate.execute("COMMIT");

        var newTitle = "updatedTitleFinal";
        var newContent = "updatedContent";

        noteRepo.updateNote(newTitle, newContent, insertedId);

        var notesAfterUpdate = jdbcTemplate.query(
                "select * from notes where id=?",
                NoteRepo.getNoteRowMapper(),
                insertedId
        );

        if (notesAfterUpdate.isEmpty())
            throw new EmptyResultDataAccessException("No note found: " + insertedId, 1);

        Note updatedNote = notesAfterUpdate.get(0);

        assertEquals(newTitle, updatedNote.title());
        assertEquals(newContent, updatedNote.content());
    }


    @Test
    @DisplayName("Test of repository - delete note")
    @Order(3)
    void deleteNote() {
        jdbcTemplate.update(
                "insert into notes(date_of_update, title, content) values(?,?,?)",
                note.dateTime(), note.title(), note.content()
        );
        jdbcTemplate.execute("COMMIT");

        var notesAfterInsert = jdbcTemplate.query(
                "select * from notes",
                NoteRepo.getNoteRowMapper()
        );

        if (notesAfterInsert.isEmpty())
            throw new EmptyResultDataAccessException("Table is empty", 1);

        Note insertedNote = notesAfterInsert.get(0);

        noteRepo.deleteNote(insertedNote.id());

        var notesAfterDelete = jdbcTemplate.query(
                "select * from notes",
                NoteRepo.getNoteRowMapper()
        );

        assertTrue(notesAfterDelete.isEmpty());
    }}
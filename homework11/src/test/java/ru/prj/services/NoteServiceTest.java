package ru.prj.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import ru.prj.dto.NoteDTO;
import ru.prj.models.Note;
import ru.prj.repositories.impl.NoteRepo;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("sandbox")
class NoteServiceTest {

    private Note note;
    private NoteDTO noteDTO;

    @Mock
    private NoteRepo noteRepo;

    @InjectMocks
    private NoteService noteService;


    @BeforeEach
    void setUp() {
        note = new Note(1L, LocalDateTime.now(), "TestTitle", "TestContent");
        noteDTO = new NoteDTO("TestTitle", "TestContent");
    }

    @Test
    @DisplayName("Test of service - creating note")
    void createNote() {
        doNothing().when(noteRepo).createNote(any(Note.class));

        noteService.createNote(noteDTO);

        verify(noteRepo).createNote(any(Note.class));
        System.out.println("Test of service - creating note successful -> " + LocalDateTime.now());
    }

    @Test
    @DisplayName("Test of service - get notes")
    void getNotes() {
        when(noteRepo.getNotes()).thenReturn(List.of(note));

        List<Note> notes = noteService.getNotes();

        assertEquals(1, notes.size());
        assertEquals("TestTitle", notes.get(0).title());
        verify(noteRepo).getNotes();
        System.out.println("Test of service - get notes successful -> " + LocalDateTime.now());
    }

    @Test
    @DisplayName("Test of service - update note")
    void updateNote() {
        doNothing().when(noteRepo).updateNote(anyString(), anyString(), anyLong());

        noteService.updateNote(note.title(), note.content(), note.id());

        verify(noteRepo).updateNote(note.title(), note.content(), note.id());
        System.out.println("Test of service - update note successful -> " + LocalDateTime.now());
    }

    @Test
    @DisplayName("Test of service - delete note")
    void deleteNote() {
        doNothing().when(noteRepo).deleteNote(anyLong());

        noteService.deleteNote(note.id());

        verify(noteRepo).deleteNote(note.id());

        System.out.println("Test of service - delete note successful -> " + LocalDateTime.now());
    }
}
package ru.prj.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.prj.dto.NoteDTO;
import ru.prj.models.Note;
import ru.prj.services.NoteService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ActiveProfiles("sandbox")
class NoteControllerTest {

    private Note note;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NoteService noteService;

    @BeforeEach
    public void setUp() {
        note = new Note(1L, LocalDateTime.now(), "TestTitle", "TestContent");
    }

    @Test
    @DisplayName("Test for home page view")
    void home() throws Exception {
        mockMvc.perform(get("/mvc"))
                .andExpect(view().name("welcome"));
    }

    @Test
    @DisplayName("Test for retrieving notes")
    void getNotes() throws Exception {
        when(noteService.getNotes()).thenReturn(List.of(note));

        mockMvc.perform(get("/mvc/all"))
                .andExpect(view().name("view-notes"))
                .andExpect(model().attributeExists("notes"))
                .andExpect(model().attribute("notes", List.of(note)));
    }

    @Test
    @DisplayName("Test for editing note")
    void editNote() throws Exception {
        when(noteService.getNotes()).thenReturn(List.of(note));

        mockMvc.perform(get("/mvc/edit/" + note.id()))
                .andExpect(view().name("edit-note"))
                .andExpect(model().attributeExists("note"))
                .andExpect(model().attribute("note", note));
    }

    @Test
    @DisplayName("Test for create-form page view")
    void createNoteForm() throws Exception {
        mockMvc.perform(get("/mvc/create"))
                .andExpect(view().name("create-note"));
    }

    @Test
    @DisplayName("Test for creating note")
    void createNote() throws Exception {
        doNothing().when(noteService).createNote(any(NoteDTO.class));

        mockMvc.perform(post("/mvc/create")
                    .param("title", note.title())
                    .param("content", note.content()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mvc/all"));

        verify(noteService).createNote(
                argThat(noteDTO ->
                                noteDTO.title().equals(note.title()) &&
                                noteDTO.content().equals(note.content())));
    }

    @Test
    @DisplayName("Test for updating note")
    void updateNote() throws Exception {
        doAnswer( invocationOnMock -> {
            System.out.println("updateNote вызван с аргументами: " +
                    invocationOnMock.getArgument(0) + "," +
                    invocationOnMock.getArgument(1) + ", " +
                    invocationOnMock.getArgument(2));
            return null;
        }).when(noteService).updateNote(anyString(), anyString(), anyLong());

        mockMvc.perform(post("/mvc/update/" + note.id())
                        .param("title", note.title())
                        .param("content", note.content()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mvc/all"));

        verify(noteService).updateNote(note.title(), note.content(), note.id());
    }

    @Test
    @DisplayName("Test for deleting note")
    void deleteNote() throws Exception {
        doAnswer(invocationOnMock -> {
            Long noteId = invocationOnMock.getArgument(0);
            System.out.println("deleteNote вызван с аргументом: " + noteId);
            return null;
        }).when(noteService).deleteNote(anyLong());

        mockMvc.perform(delete("/mvc/delete/" + note.id()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mvc/all"));

        verify(noteService).deleteNote(1L);
    }
}
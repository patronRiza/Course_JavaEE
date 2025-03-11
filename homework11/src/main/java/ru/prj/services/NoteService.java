package ru.prj.services;

import org.springframework.stereotype.Service;
import ru.prj.dto.NoteDTO;
import ru.prj.models.Note;
import ru.prj.repositories.impl.NoteRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class NoteService {

    private final NoteRepo noteRepo;

    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }


    public void createNote(NoteDTO noteDTO) {
        noteRepo.createNote(
                new Note(
                        new Random().nextLong(10),
                        LocalDateTime.now(),
                        noteDTO.title(),
                        noteDTO.content()));
    }

    public List<Note> getNotes() {
        return noteRepo.getNotes();
    }

    public void updateNote(String title, String content, Long id) {
        noteRepo.updateNote(title, content, id);
    }

    public void deleteNote(Long id) {
        noteRepo.deleteNote(id);
    }
}

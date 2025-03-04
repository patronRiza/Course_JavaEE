package ru.prj.controllers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.prj.dto.NoteDTO;
import ru.prj.models.Note;
import ru.prj.services.NoteService;

import java.util.List;

@Controller
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "debug")
@RequestMapping("/rest/notes")
public class NoteRestController {

    private final NoteService noteService;

    public NoteRestController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping
    public ResponseEntity<Void> createNote(@RequestBody NoteDTO noteDTO) {
        noteService.createNote(noteDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Note>> getNotes() {
        return new ResponseEntity<>(noteService.getNotes(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNote(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String content) {
        noteService.updateNote(title, content, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

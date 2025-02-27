package ru.prj.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.prj.dto.NoteDTO;
import ru.prj.models.Note;
import ru.prj.services.NoteService;

@Controller
@Profile({"sandbox", "production"})
@RequestMapping("/mvc")
public class NoteController {

    private static final String REDIRECT_TO_ALL_NOTES = "redirect:/mvc/all";

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String home() {
        return "welcome";
    }

    @GetMapping("/all")
    public String getNotes(Model model) {
        model.addAttribute("notes", noteService.getNotes());
        return "view-notes";
    }

    @GetMapping("/edit/{id}")
    public String editNote(@PathVariable Long id, Model model) {
        Note note = noteService.getNotes().stream()
                .filter(n -> n.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Переданная заметка не найдена: " + id));
        model.addAttribute("note", note);
        return "edit-note"; // Имя шаблона edit-note.html
    }

    @GetMapping("/create")
    public String createNoteForm() {
        return "create-note";
    }

    @PostMapping("/create")
    public String createNote(@RequestParam String title, @RequestParam String content) {
        noteService.createNote(new NoteDTO(title, content));
        return REDIRECT_TO_ALL_NOTES;
    }

    @PostMapping("/update/{id}")
    public String updateNote(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String content) {
        noteService.updateNote(title, content, id);
        return REDIRECT_TO_ALL_NOTES;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return REDIRECT_TO_ALL_NOTES;
    }
}

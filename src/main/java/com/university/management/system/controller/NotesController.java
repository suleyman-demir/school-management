package com.university.management.system.controller;

import com.university.management.system.model.dto.NotesDto;
import com.university.management.system.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/university/notes")

public class NotesController {
    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<String> addNote(@RequestParam String studentId,
                                          @RequestParam String lessonId,
                                          @RequestParam Integer noteValue) {
        noteService.addNoteByStudentId(studentId, lessonId, noteValue);
        return ResponseEntity.ok("Note added successfully for student ID: " + studentId + " and lesson ID: " + lessonId);
    }

    @GetMapping("/{lessonId}/{studentId}")
    public ResponseEntity<NotesDto> getNoteByLessonIdAndStudentId(@PathVariable String lessonId, @PathVariable String studentId) {
        NotesDto notesDto = noteService.getNoteByLessonIdAndStudentId(lessonId, studentId);
        if (notesDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notesDto);

    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<NotesDto> getAllNotesByLessonID(@PathVariable String lessonId) {
        noteService.getAllNotesByLessonID(lessonId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{lessonId}/{studentId}")
    public ResponseEntity<String> deleteNoteByLessonIdAndStudentId(@PathVariable String lessonId, @PathVariable String studentId) {
        noteService.deleteNoteByLessonIdAndStudentId(lessonId, studentId);
        return ResponseEntity.ok("Note deleted successfully for student ID: " + studentId + " and lesson ID: " + lessonId);
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<String> deleteAllNotesByLessonId(@PathVariable String lessonId) {
        noteService.deleteAllNotesByLessonId(lessonId);
        return ResponseEntity.ok("All notes deleted successfully for lesson ID: " + lessonId);
    }

    @PutMapping("/{lessonId}/{studentId}")
    public ResponseEntity<String> updateNoteByLessonIdAndStudentId(@PathVariable String lessonId, @PathVariable String studentId, @RequestParam Integer newNote) {
        noteService.updateNoteByLessonIdAndStudentId(lessonId, studentId, newNote);
        return ResponseEntity.ok("Note updated successfully for student ID: " + studentId + " and lesson ID: " + lessonId);
    }




}

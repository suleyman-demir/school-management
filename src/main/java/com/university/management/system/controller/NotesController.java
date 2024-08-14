package com.university.management.system.controller;

import com.university.management.system.model.dto.NotesDto;
import com.university.management.system.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api/university/notes")
public class NotesController {

    private static final Logger logger = LoggerFactory.getLogger(NotesController.class);
    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<String> addNote(@RequestParam String studentId,
                                          @RequestParam String lessonId,
                                          @RequestParam Integer noteValue) {
        logger.info("Received request to add note for student ID: {} and lesson ID: {}", studentId, lessonId);
        noteService.addNoteByStudentId(studentId, lessonId, noteValue);
        return ResponseEntity.ok("Note added successfully for student ID: " + studentId + " and lesson ID: " + lessonId);
    }

    @GetMapping("/{lessonId}/{studentId}")
    public ResponseEntity<NotesDto> getNoteByLessonIdAndStudentId(@PathVariable String lessonId, @PathVariable String studentId) {
        logger.info("Received request to get note for student ID: {} and lesson ID: {}", studentId, lessonId);
        NotesDto notesDto = noteService.getNoteByLessonIdAndStudentId(lessonId, studentId);
        return notesDto != null ? ResponseEntity.ok(notesDto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<NotesDto> getAllNotesByLessonID(@PathVariable String lessonId) {
        logger.info("Received request to get all notes for lesson ID: {}", lessonId);
        NotesDto notesDto = noteService.getAllNotesByLessonID(lessonId);
        return notesDto != null ? ResponseEntity.ok(notesDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{lessonId}/{studentId}")
    public ResponseEntity<String> deleteNoteByLessonIdAndStudentId(@PathVariable String lessonId, @PathVariable String studentId) {
        logger.info("Received request to delete note for student ID: {} and lesson ID: {}", studentId, lessonId);
        noteService.deleteNoteByLessonIdAndStudentId(lessonId, studentId);
        return ResponseEntity.ok("Note deleted successfully for student ID: " + studentId + " and lesson ID: " + lessonId);
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<String> deleteAllNotesByLessonId(@PathVariable String lessonId) {
        logger.info("Received request to delete all notes for lesson ID: {}", lessonId);
        noteService.deleteAllNotesByLessonId(lessonId);
        return ResponseEntity.ok("All notes deleted successfully for lesson ID: " + lessonId);
    }

    @PutMapping("/{lessonId}/{studentId}")
    public ResponseEntity<String> updateNoteByLessonIdAndStudentId(@PathVariable String lessonId,
                                                                   @PathVariable String studentId,
                                                                   @RequestParam Integer newNote) {
        logger.info("Received request to update note for student ID: {} and lesson ID: {}", studentId, lessonId);
        noteService.updateNoteByLessonIdAndStudentId(lessonId, studentId, newNote);
        return ResponseEntity.ok("Note updated successfully for student ID: " + studentId + " and lesson ID: " + lessonId);
    }
}

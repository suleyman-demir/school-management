package com.university.management.system.service;

import com.university.management.system.model.LessonEntity;
import com.university.management.system.model.NotesEntity;
import com.university.management.system.model.StudentEntity;
import com.university.management.system.model.dto.NotesDto;
import com.university.management.system.repository.LessonRepository;
import com.university.management.system.repository.NotesRepository;
import com.university.management.system.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {
    private final NotesRepository notesRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    public NoteService(NotesRepository notesRepository, StudentRepository studentRepository, LessonRepository lessonRepository) {
        this.notesRepository = notesRepository;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
    }

    public void addNoteByStudentId(String studentId, String lessonId, Integer noteValue) {
        logger.info("Adding note for Student ID: {} and Lesson ID: {}", studentId, lessonId);

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        LessonEntity lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with ID: " + lessonId));

        NotesEntity notesEntity = new NotesEntity();
        notesEntity.setStudent(student);
        notesEntity.setLessons(lesson);
        notesEntity.setStudentName(student.getStudentName());
        notesEntity.setLessonName(lesson.getLessonName());
        notesEntity.setNote(noteValue);

        notesRepository.save(notesEntity);
        logger.info("Note added successfully for Student ID: {} and Lesson ID: {}", studentId, lessonId);
    }

    public NotesDto getNoteByLessonIdAndStudentId(String lessonId, String studentId) {
        logger.info("Fetching note for Lesson ID: {} and Student ID: {}", lessonId, studentId);
        return notesRepository.findByStudentIdAndId(studentId, lessonId)
                .map(NotesDto::convert)
                .orElse(null);
    }

    public NotesDto getAllNotesByLessonID(String lessonId) {
        logger.info("Fetching all notes for Lesson ID: {}", lessonId);
        return notesRepository.findById(lessonId)
                .map(NotesDto::convert)
                .orElse(null);
    }

    public void deleteNoteByLessonIdAndStudentId(String lessonId, String studentId) {
        logger.info("Deleting note for Lesson ID: {} and Student ID: {}", lessonId, studentId);
        notesRepository.deleteByStudentIdAndId(studentId, lessonId);
        logger.info("Note deleted successfully for Lesson ID: {} and Student ID: {}", lessonId, studentId);
    }

    public void deleteAllNotesByLessonId(String lessonId) {
        logger.info("Deleting all notes for Lesson ID: {}", lessonId);
        notesRepository.deleteById(lessonId);
        logger.info("All notes deleted successfully for Lesson ID: {}", lessonId);
    }

    public void updateNoteByLessonIdAndStudentId(String lessonId, String studentId, Integer newNote) {
        logger.info("Updating note for Lesson ID: {} and Student ID: {}", lessonId, studentId);
        notesRepository.findByStudentIdAndId(studentId, lessonId)
                .ifPresent(note -> {
                    note.setNote(newNote);
                    notesRepository.save(note);
                    logger.info("Note updated successfully for Lesson ID: {} and Student ID: {}", lessonId, studentId);
                });
    }
}

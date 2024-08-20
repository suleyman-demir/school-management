package com.university.management.system.service;

import com.university.management.system.exception.ResourceNotFoundException;
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
        logger.info("Öğrenci ID'si {} ve Ders ID'si {} için not ekleniyor.", studentId, lessonId);

        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip öğrenci bulunamadı: " + studentId));

        LessonEntity lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip ders bulunamadı: " + lessonId));

        NotesEntity notesEntity = new NotesEntity();
        notesEntity.setStudent(student);
        notesEntity.setLessons(lesson);
        notesEntity.setStudentName(student.getStudentName());
        notesEntity.setLessonName(lesson.getLessonName());
        notesEntity.setNote(noteValue);

        notesRepository.save(notesEntity);
        logger.info("Öğrenci ID'si {} ve Ders ID'si {} için not başarıyla eklendi.", studentId, lessonId);
    }

    public NotesDto getNoteByLessonIdAndStudentId(String lessonId, String studentId) {
        logger.info("Ders ID'si {} ve Öğrenci ID'si {} için not getiriliyor.", lessonId, studentId);
        return notesRepository.findByStudentIdAndId(studentId, lessonId)
                .map(NotesDto::convert)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'lerle eşleşen not bulunamadı."));
    }

    public NotesDto getAllNotesByLessonID(String lessonId) {
        logger.info("Ders ID'si {} için tüm notlar getiriliyor.", lessonId);
        return notesRepository.findById(lessonId)
                .map(NotesDto::convert)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip ders için not bulunamadı: " + lessonId));
    }

    public void deleteNoteByLessonIdAndStudentId(String lessonId, String studentId) {
        logger.info("Ders ID'si {} ve Öğrenci ID'si {} için not siliniyor.", lessonId, studentId);
        NotesEntity note = notesRepository.findByStudentIdAndId(studentId, lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'lerle eşleşen not bulunamadı."));
        notesRepository.delete(note);
        logger.info("Ders ID'si {} ve Öğrenci ID'si {} için not başarıyla silindi.", lessonId, studentId);
    }

    public void deleteAllNotesByLessonId(String lessonId) {
        logger.info("Ders ID'si {} için tüm notlar siliniyor.", lessonId);
        LessonEntity lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip ders bulunamadı: " + lessonId));
        notesRepository.deleteById(lessonId);
        logger.info("Ders ID'si {} için tüm notlar başarıyla silindi.", lessonId);
    }

    public void updateNoteByLessonIdAndStudentId(String lessonId, String studentId, Integer newNote) {
        logger.info("Ders ID'si {} ve Öğrenci ID'si {} için not güncelleniyor.", lessonId, studentId);
        NotesEntity note = notesRepository.findByStudentIdAndId(studentId, lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'lerle eşleşen not bulunamadı."));
        note.setNote(newNote);
        notesRepository.save(note);
        logger.info("Ders ID'si {} ve Öğrenci ID'si {} için not başarıyla güncellendi.", lessonId, studentId);
    }
}

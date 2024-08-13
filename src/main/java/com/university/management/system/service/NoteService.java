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
        logger.info("Adding note for Student ID: " + studentId + " and Lesson ID: " + lessonId);

        // Öğrenciyi bul
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        // Dersi bul
        LessonEntity lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with ID: " + lessonId));

        // Yeni not nesnesi oluştur
        NotesEntity notesEntity = new NotesEntity();
        notesEntity.setStudent(student);
        notesEntity.setLessons(lesson);
        notesEntity.setStudentName(student.getStudentName());
        notesEntity.setLessonName(lesson.getLessonName());
        notesEntity.setNote(noteValue);

        // Notu kaydet
        notesRepository.save(notesEntity);
    }

    public NotesDto getNoteByLessonIdAndStudentId(String lessonId, String studentId) {
        logger.info("Requested Note : " + lessonId + " " + studentId);
        return notesRepository.findByStudentIdAndId(studentId, lessonId)
               .map(NotesDto::convert)
               .orElse(null);
    }

    public NotesDto getAllNotesByLessonID(String lessonId){
        logger.info("Requested All Notes Of : "+ lessonId);
        return notesRepository.findById(lessonId)
                .map(NotesDto::convert)
                .orElse(null);
    }

    public void deleteNoteByLessonIdAndStudentId(String lessonId, String studentId) {
        logger.info("Deleting Note : " + lessonId + " " + studentId);
        notesRepository.deleteByStudentIdAndId(studentId, lessonId);
    }

    public void deleteAllNotesByLessonId(String lessonId){
        logger.info("Deleting All Notes Of : "+ lessonId);
        notesRepository.deleteById(lessonId);
    }

    public void updateNoteByLessonIdAndStudentId(String lessonId , String studentId, Integer newNote){
        logger.info("Updating Note : "+lessonId+ " "+" Student ID : "+ studentId);
        notesRepository.findByStudentIdAndId(studentId,lessonId)
                .ifPresent(note -> {
                    NotesDto updatedNoteDto= new NotesDto(
                            note.getId(),
                            note.getStudentName(),
                            lessonId,
                            newNote
                    );
                    notesRepository.save(NotesDto.convert(updatedNoteDto));
                });

    }







}


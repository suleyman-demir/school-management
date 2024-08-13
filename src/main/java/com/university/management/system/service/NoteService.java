package com.university.management.system.service;

import com.university.management.system.model.dto.NotesDto;
import com.university.management.system.repository.NotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    public final NotesRepository notesRepository;

    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    public NoteService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public void addNote(NotesDto notesDto) {
        logger.info("Adding note " + notesDto.note());
        notesRepository.save(NotesDto.convert(notesDto));
    }

    public NotesDto getNoteByLessonIdAndStudentId(String lessonId, String studentId) {
        logger.info("Requested Note : " + lessonId + " " + studentId);
        return notesRepository.findByStudentIdAndLessonId(studentId, lessonId)
               .map(NotesDto::convert)
               .orElse(null);
    }

    public NotesDto getAllNotesByLessonID(String lessonId){
        logger.info("Requested All Notes Of : "+ lessonId);
        return notesRepository.findByLessonId(lessonId)
                .map(NotesDto::convert)
                .orElse(null);
    }

    public void deleteNoteByLessonIdAndStudentId(String lessonId, String studentId) {
        logger.info("Deleting Note : " + lessonId + " " + studentId);
        notesRepository.deleteByStudentIdAndLessonId(studentId, lessonId);
    }

    public void deleteAllNotesByLessonId(String lessonId){
        logger.info("Deleting All Notes Of : "+ lessonId);
        notesRepository.deleteByLessonId(lessonId);
    }

    public void updateNoteByLessonIdAndStudentId(String lessonId , String studentId, Integer newNote){
        logger.info("Updating Note : "+lessonId+ " "+" Student ID : "+ studentId);
        notesRepository.findByStudentIdAndLessonId(studentId,lessonId)
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


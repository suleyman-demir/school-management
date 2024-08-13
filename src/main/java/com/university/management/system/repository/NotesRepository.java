package com.university.management.system.repository;

import com.university.management.system.model.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotesRepository extends JpaRepository<NotesEntity,String> {

    Optional<NotesEntity> findByStudentIdAndLessonId( String studentId, String lessonId);
    Optional<NotesEntity> findByLessonId( String lessonId);
    Optional<NotesEntity> deleteByStudentIdAndLessonId( String studentId, String lessonId);
    Optional<NotesEntity> deleteByLessonId( String lessonId);
}

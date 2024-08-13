package com.university.management.system.repository;

import com.university.management.system.model.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotesRepository extends JpaRepository<NotesEntity,String> {

    Optional<NotesEntity> findByStudentIdAndId(String studentId, String lessonId);
    Optional<NotesEntity> findById(String lessonId);
    void deleteByStudentIdAndId(String studentId, String lessonId);
    void deleteById(String lessonId);
}

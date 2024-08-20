package com.university.management.system.repository;

import com.university.management.system.model.LessonEntity;
import com.university.management.system.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LessonRepository extends JpaRepository<LessonEntity,String> {
    void deleteById(String lessonId);






}

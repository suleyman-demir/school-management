package com.university.management.system.repository;

import com.university.management.system.model.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LessonRepository extends JpaRepository<LessonEntity,String> {
    void deleteById(String lessonId);



}

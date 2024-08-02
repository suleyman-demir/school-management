package com.university.management.system.repository;

import com.university.management.system.model.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity,String> {

    Optional<TeacherEntity>findByTeacherName(String teacherName);
    Optional<TeacherEntity>findByTeacherLessonsName(String teacherLessonsName);

}

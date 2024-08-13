package com.university.management.system.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class TeacherEntity {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String teacherName;
    private String teacherLessonsName;

    public TeacherEntity(String id, String teacherName, String teacherLessonsName) {
        this.id = id;
        this.teacherName = teacherName;
        this.teacherLessonsName = teacherLessonsName;
    }

    public TeacherEntity(String teacherName, String teacherLessonsName) {
        this.id = "";
        this.teacherName = teacherName;
        this.teacherLessonsName = teacherLessonsName;
    }

    public TeacherEntity() {

    }


    public String getId() {
        return id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherLessonsName() {
        return teacherLessonsName;
    }
}

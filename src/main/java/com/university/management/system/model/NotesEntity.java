package com.university.management.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class NotesEntity {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String studentName;
    private String lessonName; //////////////////BURAYA LİSTLER EKLENECEK

    public NotesEntity(String id, String studentName, String lessonName) {
        this.id = id;
        this.studentName = studentName;
        this.lessonName = lessonName;
    }

    public NotesEntity(String studentName, String lessonName) {
        this.id= "";
        this.studentName=studentName;
        this.lessonName=lessonName;
    }

    public NotesEntity() {
        //////////////////////////////////////////
    }

    public String getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getLessonName() {
        return lessonName;
    }
}

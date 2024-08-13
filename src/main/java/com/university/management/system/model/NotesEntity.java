package com.university.management.system.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
public class NotesEntity {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String studentName;
    private String lessonName;
    private Integer note;
    @ManyToOne
    private LessonEntity lessons; //////////////////BURAYA LİSTLER EKLENECEK

    public NotesEntity(String id, String studentName, String lessonName, LessonEntity lessons) {
        this.id = id;
        this.studentName = studentName;
        this.lessonName = lessonName;
        this.lessons = lessons;
    }

    public NotesEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public LessonEntity getLessons() {
        return lessons;
    }

    public void setLessons(LessonEntity lessons) {
        this.lessons = lessons;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }
}

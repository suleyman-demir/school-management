package com.university.management.system.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
public class LessonEntity {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String lessonName;
    private String teacherName;

    @OneToMany
    private List<NotesEntity> lessonNotes = new ArrayList<>();////////////////////

    public LessonEntity(String id, String lessonName, String teacherName, List<NotesEntity> lessonNotes) {
        this.id = id;
        this.lessonName = lessonName;
        this.teacherName = teacherName;
        this.lessonNotes = lessonNotes;
    }

    public LessonEntity(String lessonName, String teacherName, List<NotesEntity> lessonNotes) {
        this.lessonName = lessonName;
        this.teacherName = teacherName;
        this.lessonNotes = lessonNotes;
    }


    public LessonEntity() {
        //////////////////////////////////
    }

    public String getId() {
        return id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public List<NotesEntity> getLessonNotes() {
        return lessonNotes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setLessonNotes(List<NotesEntity> lessonNotes) {
        this.lessonNotes = lessonNotes;
    }
}

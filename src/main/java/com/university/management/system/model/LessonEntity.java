package com.university.management.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class LessonEntity {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String lessonName;
    private String teacherName;
    private String lessonNotes;////////////////////

    public LessonEntity(String id, String lessonName, String teacherName, String lessonNotes) {
        this.id = id;
        this.lessonName = lessonName;
        this.teacherName = teacherName;
        this.lessonNotes = lessonNotes;
    }
    public LessonEntity(String lessonName, String teacherName, String lessonNotes) {
        this.id = "";
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

    public String getLessonNotes() {
        return lessonNotes;
    }
}

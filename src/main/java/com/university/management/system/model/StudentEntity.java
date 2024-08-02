package com.university.management.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class StudentEntity {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String studentName;
    private String studentNumber;
    private Integer studentLessonNote;
    private String studentLessonNames;

    public StudentEntity(String id, String studentName, String studentNumber, Integer studentLessonNote, String studentLessonNames) {
        this.id = id;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.studentLessonNote = studentLessonNote;
        this.studentLessonNames = studentLessonNames;
    }

    public StudentEntity(String studentName, String studentNumber, Integer studentLessonNote, String studentLessonNames) {
        this.id = "";
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.studentLessonNote = studentLessonNote;
        this.studentLessonNames = studentLessonNames;
    }

    public StudentEntity() {
         ////////////////////////////////////////
    }

    public String getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public Integer getStudentLessonNote() {
        return studentLessonNote;
    }

    public String getStudentLessonNames() {
        return studentLessonNames;
    }
}

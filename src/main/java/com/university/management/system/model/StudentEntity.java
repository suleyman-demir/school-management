package com.university.management.system.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
public class StudentEntity {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String studentId;
    private String studentName;
    private String studentNumber;
    @OneToMany(mappedBy = "student")
    private List<NotesEntity> studentLessonNote;
    @OneToMany(mappedBy = "student")
    private List<LessonEntity> studentLessonNames;

    public StudentEntity(String studentId, String studentName, String studentNumber, List<NotesEntity> studentLessonNote, List<LessonEntity> studentLessonNames) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.studentLessonNote = studentLessonNote;
        this.studentLessonNames = studentLessonNames;
    }

    public StudentEntity() {

    }

    public String getId() {
        return studentId;
    }

    public void setId(String id) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public List<NotesEntity> getStudentLessonNote() {
        return studentLessonNote;
    }

    public void setStudentLessonNote(List<NotesEntity> studentLessonNote) {
        this.studentLessonNote = studentLessonNote;
    }

    public List<LessonEntity> getStudentLessonNames() {
        return studentLessonNames;
    }

    public void setStudentLessonNames(List<LessonEntity> studentLessonNames) {
        this.studentLessonNames = studentLessonNames;
    }
}

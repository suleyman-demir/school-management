package com.university.management.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notes")
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
    @JoinColumn(name="student_id")
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name="lesson_id")
    private LessonEntity lessons;

    public NotesEntity(String id, String studentName, String lessonName, StudentEntity student, LessonEntity lessons) {
        this.id = id;
        this.studentName = studentName;
        this.lessonName = lessonName;
        this.student = student;
        this.lessons = lessons;
    }
}

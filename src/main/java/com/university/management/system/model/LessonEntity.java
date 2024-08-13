package com.university.management.system.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import com.university.management.system.model.StudentEntity;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor

@Data
@Entity
public class LessonEntity {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String lessonName;
    private String teacherName;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_lesson",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<StudentEntity> student = new ArrayList<>();


    @OneToMany(mappedBy = "lessons")
    @JsonManagedReference
    private List<NotesEntity> lessonNotes = new ArrayList<>();


    public LessonEntity(String id, String lessonName, String teacherName, TeacherEntity teacher, List<StudentEntity> student, List<NotesEntity> lessonNotes) {
        this.id = id;
        this.lessonName = lessonName;
        this.teacherName = teacherName;
        this.teacher = teacher;
        this.student = student;
        this.lessonNotes = lessonNotes;
    }

    public LessonEntity(String id, String lessonName, String teacherName, Object o) {

    }


}

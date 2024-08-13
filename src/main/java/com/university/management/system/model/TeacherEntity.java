package com.university.management.system.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TeacherEntity {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String teacherId;
    private String teacherName;
    @OneToMany(mappedBy = "teacher")
    private List<LessonEntity> teacherLessonsName;


    public String getId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherLessonsName() {
        return teacherLessonsName.toString();
    }
}

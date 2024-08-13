package com.university.management.system.model.dto;

import com.university.management.system.model.LessonEntity;
import com.university.management.system.model.NotesEntity;

import java.util.Optional;

public record NotesDto(
        String id,
       String studentName,
       String lessonName,
       Integer note
) {
    public NotesDto(String lessonName, Integer note) {
        this(null, null, lessonName, note);
    }

    public static NotesDto convert(NotesEntity from){
        return new NotesDto(
                from.getId(),
                from.getStudentName(),
                from.getLessonName(),
                from.getNote()
        );
    }

    public static NotesEntity convert(NotesDto from){
        return new NotesEntity(
                from.id,
                from.studentName,
                from.lessonName,
                null
        );
    }

    public static void setId(String id) {

    }
}

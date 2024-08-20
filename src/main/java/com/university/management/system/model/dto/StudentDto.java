package com.university.management.system.model.dto;

import com.university.management.system.model.LessonEntity;
import com.university.management.system.model.StudentEntity;

import java.util.List;

public record StudentDto(

        String studentId,
        String name,
        String number,
        List<NotesDto> notes,
        List<String> studentLessonNames
) {
    public static StudentDto convert(StudentEntity from) {
        return new StudentDto(
                from.getId(),
                from.getStudentName(),
                from.getStudentNumber(),
                from.getStudentLessonNote().stream()
                        .map(note -> new NotesDto(note.getLessonName(), note.getNote()))
                        .toList(),
                from.getStudentLessonNames().stream().map(LessonEntity::getLessonName).toList()


        );


    }
    public static StudentEntity convert(StudentDto from){
        return new StudentEntity(
                from.studentId,
                from.name,
                from.number,
                null,
                null
        );
    }

}

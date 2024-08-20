package com.university.management.system.model.dto;

import com.university.management.system.model.LessonEntity;

import java.util.List;

public record LessonDto(
        String id,
        String lessonName,
        List<StudentDto> students


) {
    public static LessonDto convert(LessonEntity from) {
        List<StudentDto> students = from.getStudent().stream()
                .map(StudentDto::convert)
                .toList();

        return new LessonDto(
                from.getId(),
                from.getLessonName(),
                students
        );

    }

    public LessonEntity toEntity() {
        return new LessonEntity(
                id(),
                lessonName(),

                null
        );
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String lessonName() {
        return lessonName;
    }

    @Override
    public List<StudentDto> students() {
        return students;
    }
}

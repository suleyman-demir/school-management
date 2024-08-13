package com.university.management.system.model.dto;

import com.university.management.system.model.LessonEntity;

import java.util.List;

public record LessonDto(
        String id,
        String lessonName,
        String teacherName,
        List<String>studentIds




) {
    public static LessonDto convert(LessonEntity from) {
        return new LessonDto(
                from.getId(),
                from.getLessonName(),
                from.getTeacherName(),
                null
        );

    }

    public static LessonEntity convert(LessonDto from) {
        return new LessonEntity(
                from.id,
                from.lessonName,
                from.teacherName,
                null
        );
    }
}

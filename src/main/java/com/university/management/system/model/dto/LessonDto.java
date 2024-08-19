package com.university.management.system.model.dto;

import com.university.management.system.model.LessonEntity;

import java.util.List;

public record LessonDto(
        String id,
        String lessonName,
        List<String>studentIds




) {
    public static LessonDto convert(LessonEntity from) {
        return new LessonDto(
                from.getId(),
                from.getLessonName(),
                null
        );

    }

    public LessonEntity toEntity() {
        return new LessonEntity(
                id(),
                lessonName(),
                null
        );
    }
}

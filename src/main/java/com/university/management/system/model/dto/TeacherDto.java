package com.university.management.system.model.dto;

import com.university.management.system.model.TeacherEntity;

import java.util.Collections;
import java.util.List;

public record TeacherDto(
        String id,
        String name,
        List<String> lessonNames



) {
        public static TeacherDto convert (TeacherEntity from){
            return new TeacherDto(from.getId(), from.getTeacherName(), Collections.singletonList(from.getTeacherLessonsName()));
        }

        public static TeacherEntity convert (TeacherDto from){
            return new
                    TeacherEntity(from.id(), from.name(),null);
        }
}

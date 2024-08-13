package com.university.management.system.model.dto;

import java.util.List;

public record UniversityDto(
        List<StudentDto> students,
        List<TeacherDto> teachers,
        List<LessonDto> courses,
        List<NotesDto> grades
) {

    
}

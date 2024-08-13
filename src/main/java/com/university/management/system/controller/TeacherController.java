package com.university.management.system.controller;

import com.university.management.system.model.dto.TeacherDto;
import com.university.management.system.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/university/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity <TeacherDto> getTeacherByTeacherId(@PathVariable String teacherId) {
        teacherService.getTeacherByTeacherId(teacherId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void>addTeacher(@RequestBody TeacherDto teacherDto) {
        teacherService.addTeacher(teacherDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<Void> updateTeacherByTeacherId(@PathVariable String teacherId, @RequestBody String newTeacherName) {
        teacherService.updateTeacherByTeacherId(teacherId, newTeacherName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacherByTeacherId(@PathVariable String teacherId) {
        teacherService.deleteTeacherByTeacherId(teacherId);
        return ResponseEntity.ok().build();
    }
}

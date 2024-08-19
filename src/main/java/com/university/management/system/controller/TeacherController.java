package com.university.management.system.controller;

import com.university.management.system.model.dto.StudentDto;
import com.university.management.system.model.dto.TeacherDto;
import com.university.management.system.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/university/teachers")
public class TeacherController {

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherDto> getTeacherByTeacherId(@PathVariable String teacherId) {
        logger.info("Received request to get teacher with ID: {}", teacherId);
        Optional<TeacherDto> teacherDtoOptional = Optional.ofNullable(teacherService.getTeacherByTeacherId(teacherId));
        return teacherDtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addTeacher(@RequestBody TeacherDto teacherDto) {
        logger.info("Received request to add a new teacher: {}", teacherDto.name());
        teacherService.addTeacher(teacherDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<Void> updateTeacherByTeacherId(@PathVariable String teacherId, @RequestBody String newTeacherName) {
        logger.info("Received request to update teacher with ID: {}", teacherId);
        teacherService.updateTeacherByTeacherId(teacherId, newTeacherName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> deleteTeacherByTeacherId(@PathVariable String teacherId) {
        logger.info("Received request to delete teacher with ID: {}", teacherId);
        teacherService.deleteTeacherByTeacherId(teacherId);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        logger.info("Received request to get all teachers");
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }


}

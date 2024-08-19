package com.university.management.system.controller;

import com.university.management.system.model.dto.LessonDto;
import com.university.management.system.service.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/api/university/lessons")
public class LessonController {

    private static final Logger logger = LoggerFactory.getLogger(LessonController.class);
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<Void> addLesson(@RequestBody LessonDto lessonDto) {
        logger.info("Received request to add lesson: {}", lessonDto.lessonName());
        lessonService.addLesson(lessonDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonDto> getLessonByLessonId(@PathVariable String lessonId) {
        logger.info("Received request to get lesson with ID: {}", lessonId);
        Optional<LessonDto> lessonDtoOptional = Optional.ofNullable(lessonService.getLessonByLessonId(lessonId));
        return lessonDtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLessonByLessonId(@PathVariable String lessonId) {
        logger.info("Received request to delete lesson with ID: {}", lessonId);
        lessonService.deleteLessonByLessonId(lessonId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<Void> updateLessonByLessonId(@PathVariable String lessonId, @RequestBody String newLessonName) {
        logger.info("Received request to update lesson with ID: {} to new name: {}", lessonId, newLessonName);
        lessonService.updateLessonByLessonId(lessonId, newLessonName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/join/student")
    public ResponseEntity<Void> joinStudentToLesson(@RequestParam String studentId, @RequestParam String lessonId) {
        logger.info("Received request to join student with ID: {} to lesson with ID: {}", studentId, lessonId);
        lessonService.joinStudentToLesson(studentId,lessonId);
        return ResponseEntity.ok().build();
    }
}

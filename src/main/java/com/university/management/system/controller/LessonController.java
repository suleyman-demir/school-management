package com.university.management.system.controller;

import com.university.management.system.model.LessonEntity;
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

    private final LessonService lessonService;


    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

   @PostMapping
    public ResponseEntity<Void>addLesson(@RequestBody LessonDto lessonDto){
        lessonService.addLesson(lessonDto);
        return ResponseEntity.ok().build();

   }

   @GetMapping("/{lessonId}")
    public ResponseEntity<LessonDto>getLessonByLessonId(@PathVariable String lessonId){
        LessonDto lessonDto=lessonService.getLessonByLessonId(lessonId);
        return lessonDto !=null ? ResponseEntity.ok(lessonDto): ResponseEntity.notFound().build();
   }

   @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLessonByLessonId(@PathVariable String lessonId){
        lessonService.deleteLessonByLessonId(lessonId);
        return ResponseEntity.ok().build();
   }

    @PutMapping("/{lessonId}")
    public ResponseEntity<Void> updateLessonByLessonId(@PathVariable String lessonId,@RequestBody String newLessonName){
        lessonService.updateLessonByLessonId(lessonId, newLessonName);
        return ResponseEntity.ok().build();
    }






}


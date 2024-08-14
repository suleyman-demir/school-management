package com.university.management.system.service;

import com.university.management.system.model.LessonEntity;
import com.university.management.system.model.dto.LessonDto;
import com.university.management.system.repository.LessonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private static final Logger logger = LoggerFactory.getLogger(LessonService.class);

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public void addLesson(LessonDto lessonDto) {
        logger.info("Adding lesson: {}", lessonDto.lessonName());
        LessonEntity lessonEntity = LessonDto.convert(lessonDto);
        lessonRepository.save(lessonEntity);
        logger.info("Lesson added successfully: {}", lessonEntity.getId());
    }

    public LessonDto getLessonByLessonId(String lessonId) {
        logger.info("Fetching lesson with ID: {}", lessonId);
        Optional<LessonEntity> lessonEntityOptional = lessonRepository.findById(lessonId);
        return lessonEntityOptional.map(LessonDto::convert).orElse(null);
    }

    public void deleteLessonByLessonId(String lessonId) {
        logger.info("Deleting lesson with ID: {}", lessonId);
        lessonRepository.deleteById(lessonId);
        logger.info("Lesson deleted successfully: {}", lessonId);
    }

    public void updateLessonByLessonId(String lessonId, String newLessonName) {
        logger.info("Updating lesson with ID: {}", lessonId);
        Optional<LessonEntity> lessonEntityOptional = lessonRepository.findById(lessonId);
        lessonEntityOptional.ifPresent(lesson -> {
            lesson.setLessonName(newLessonName);
            lessonRepository.save(lesson);
            logger.info("Lesson updated successfully: {}", lesson.getId());
        });
    }
}

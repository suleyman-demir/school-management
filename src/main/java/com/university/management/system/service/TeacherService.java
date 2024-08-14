package com.university.management.system.service;

import com.university.management.system.model.TeacherEntity;
import com.university.management.system.model.dto.TeacherDto;
import com.university.management.system.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherDto getTeacherByTeacherId(String teacherId) {
        logger.info("Fetching teacher with ID: {}", teacherId);
        Optional<TeacherEntity> teacherEntityOptional = teacherRepository.findByTeacherName(teacherId);
        return teacherEntityOptional.map(TeacherDto::convert).orElse(null);
    }

    public void addTeacher(TeacherDto teacherDto) {
        logger.info("Adding teacher: {}", teacherDto.name());
        TeacherEntity teacherEntity = TeacherDto.convert(teacherDto);
        teacherRepository.save(teacherEntity);
        logger.info("Teacher added successfully: {}", teacherEntity.getId());
    }

    public void updateTeacherByTeacherId(String teacherId, String newTeacherName) {
        logger.info("Updating teacher with ID: {}", teacherId);
        Optional<TeacherEntity> teacherEntityOptional = teacherRepository.findById(teacherId);
        teacherEntityOptional.ifPresent(teacher -> {
            teacher.setTeacherName(newTeacherName);
            teacherRepository.save(teacher);
            logger.info("Teacher updated successfully: {}", teacher.getId());
        });
    }

    public void deleteTeacherByTeacherId(String teacherId) {
        logger.info("Deleting teacher with ID: {}", teacherId);
        teacherRepository.deleteByTeacherId(teacherId);
        logger.info("Teacher deleted successfully: {}", teacherId);
    }
}

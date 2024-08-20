package com.university.management.system.service;

import com.university.management.system.exception.ResourceNotFoundException;
import com.university.management.system.model.TeacherEntity;
import com.university.management.system.model.dto.TeacherDto;
import com.university.management.system.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherDto getTeacherByTeacherId(String teacherId) {
        logger.info("ID'si {} olan öğretmen getiriliyor.", teacherId);
        TeacherEntity teacherEntity = teacherRepository.findByTeacherId(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip öğretmen bulunamadı: " + teacherId));
        return TeacherDto.convert(teacherEntity);
    }

    public void addTeacher(TeacherDto teacherDto) {
        logger.info("Öğretmen ekleniyor: {}", teacherDto.name());
        TeacherEntity teacherEntity = TeacherDto.convert(teacherDto);
        teacherRepository.save(teacherEntity);
        logger.info("Öğretmen başarıyla eklendi: {}", teacherEntity.getId());
    }

    public void updateTeacherByTeacherId(String teacherId, String newTeacherName) {
        logger.info("ID'si {} olan öğretmen güncelleniyor.", teacherId);
        TeacherEntity teacherEntity = teacherRepository.findByTeacherId(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip öğretmen bulunamadı: " + teacherId));
        teacherEntity.setTeacherName(newTeacherName);
        teacherRepository.save(teacherEntity);
        logger.info("Öğretmen başarıyla güncellendi: {}", teacherEntity.getId());
    }

    public void deleteTeacherByTeacherId(String teacherId) {
        logger.info("ID'si {} olan öğretmen siliniyor.", teacherId);
        TeacherEntity teacherEntity = teacherRepository.findByTeacherId(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip öğretmen bulunamadı: " + teacherId));
        teacherRepository.delete(teacherEntity);
        logger.info("Öğretmen başarıyla silindi: {}", teacherId);
    }

    public List<TeacherDto> getAllTeachers() {
        logger.info("Tüm öğretmenler getiriliyor.");
        return teacherRepository.findAll().stream()
                .map(TeacherDto::convert)
                .collect(Collectors.toList());
    }
}

package com.university.management.system.service;

import com.university.management.system.model.StudentEntity;
import com.university.management.system.model.dto.StudentDto;
import com.university.management.system.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(StudentDto studentDto) {
        logger.info("Adding student: {}", studentDto.name());
        StudentEntity studentEntity = StudentDto.convert(studentDto);
        studentRepository.save(studentEntity);
        logger.info("Student added successfully: {}", studentEntity.getId());
    }
    public void addStudents(List<StudentDto> studentDtos) {
        logger.info("Adding {} students", studentDtos.size());
        List<StudentEntity> studentEntities = studentDtos.stream()
                .map(StudentDto::convert)
                .collect(Collectors.toList());
        studentRepository.saveAll(studentEntities);
        logger.info("All students added successfully");
    }

    public StudentDto getStudentByStudentId(String studentId) {
        logger.info("Fetching student with ID: {}", studentId);
        Optional<StudentEntity> studentEntityOptional = studentRepository.findByStudentId(studentId);
        return studentEntityOptional.map(StudentDto::convert).orElse(null);
    }

    public void updateStudentByStudentId(String studentId, StudentDto studentDto) {
        logger.info("Updating student with ID: {}", studentId);
        Optional<StudentEntity> studentEntityOptional = studentRepository.findById(studentId);
        studentEntityOptional.ifPresent(student -> {
            student.setStudentName(studentDto.name());
            student.setStudentNumber(studentDto.number());
            studentRepository.save(student);
            logger.info("Student updated successfully: {}", student.getId());
        });
    }

    public void deleteStudentByStudentId(String studentId) {
        logger.info("Deleting student with ID: {}", studentId);
        studentRepository.deleteByStudentId(studentId);
        logger.info("Student deleted successfully: {}", studentId);
    }

    public List<StudentDto> getAllStudents() {
        logger.info("Fetching all students");
        return studentRepository.findAll().stream()
                .map(StudentDto::convert)
                .collect(Collectors.toList());
    }
}

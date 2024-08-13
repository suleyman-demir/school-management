package com.university.management.system.service;

import com.university.management.system.model.StudentEntity;
import com.university.management.system.model.dto.StudentDto;
import com.university.management.system.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(StudentDto studentDto) {
        logger.info("Adding Student : " + studentDto.name());
        studentRepository.save(StudentDto.convert(studentDto));


    }

    public StudentDto getStudentByStudentId(String studentId) {
        logger.info("Requested Student : " + studentId);
        Optional<StudentEntity> studentEntityOptional = studentRepository.findByStudentName(studentId);
        return studentEntityOptional.map(StudentDto::convert).orElse(null);

    }
    public void updateStudentByStudentId(String studentId){
        logger.info("Updating Student : " + studentId);
        Optional<StudentEntity> studentEntityOptional = studentRepository.findById(studentId);
        studentEntityOptional.ifPresent(student -> {
            StudentDto updatedStudentDto = new StudentDto(
                    student.getId(),
                    student.getStudentName(),
                    student.getStudentNumber(),
                    null,
                    null
            );
            studentRepository.save(StudentDto.convert(updatedStudentDto));
        });
    }
    public void deleteStudentByStudentId(String studentId){
        logger.info("Deleting Student : " + studentId);
        studentRepository.deleteByStudentId(studentId);
    }

    public StudentDto getAllStudentOfLessonByLessonId(String lessonId){
        logger.info("Requested Students of Lesson : " + lessonId);
        Optional<StudentEntity> studentEntityOptional = studentRepository.findByStudentLessonNames(lessonId);
        return studentEntityOptional.map(StudentDto::convert).orElse(null);
    }



}


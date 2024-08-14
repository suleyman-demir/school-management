package com.university.management.system.controller;

import com.university.management.system.model.dto.StudentDto;
import com.university.management.system.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/university/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudentByStudentId(@PathVariable String studentId) {
        logger.info("Received request to get student with ID: {}", studentId);
        Optional<StudentDto> studentDtoOptional = Optional.ofNullable(studentService.getStudentByStudentId(studentId));
        return studentDtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        logger.info("Received request to get all students");
        List<StudentDto> studentDtos = studentService.getAllStudents();
        if (studentDtos != null && !studentDtos.isEmpty()) {
            return ResponseEntity.ok(studentDtos);
        } else {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
    }

    @PostMapping
    public ResponseEntity<Void> addStudent(@RequestBody StudentDto studentDto) {
        logger.info("Received request to add a new student: {}", studentDto.name());
        studentService.addStudent(studentDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Void> updateStudentByStudentId(@PathVariable String studentId, @RequestBody StudentDto studentDto) {
        logger.info("Received request to update student with ID: {}", studentId);
        studentService.updateStudentByStudentId(studentId, studentDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/alot")
    public ResponseEntity<Void> addStudents(@RequestBody List<StudentDto> studentDtos) {
        studentService.addStudents(studentDtos);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudentByStudentId(@PathVariable String studentId) {
        logger.info("Received request to delete student with ID: {}", studentId);
        studentService.deleteStudentByStudentId(studentId);
        return ResponseEntity.ok().build();
    }
}

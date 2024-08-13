package com.university.management.system.controller;

import com.university.management.system.model.dto.StudentDto;
import com.university.management.system.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api/university/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudentByStudentId(@PathVariable String studentId){
        studentService.getStudentByStudentId(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<StudentDto> getAllStudents(){
        studentService.getAllStudents();
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> addStudent(@RequestBody StudentDto studentDto){
        studentService.addStudent(studentDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Void> updateStudentByStudentId (@PathVariable String studentId, @RequestBody StudentDto studentDto){
        studentService.updateStudentByStudentId(studentId,studentDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudentByStudentId(@PathVariable String studentId){
        studentService.deleteStudentByStudentId(studentId);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/{lessonId}")
//    public ResponseEntity<StudentDto> getAllStudentOfLessonByLessonId(@PathVariable String lessonId){
//        studentService.getAllStudentOfLessonByLessonId(lessonId);
//        return ResponseEntity.ok().build();
//    }


}

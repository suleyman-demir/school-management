package com.university.management.system.service;

import com.university.management.system.model.LessonEntity;
import com.university.management.system.model.StudentEntity;
import com.university.management.system.model.TeacherEntity;
import com.university.management.system.model.dto.LessonDto;
import com.university.management.system.repository.LessonRepository;
import com.university.management.system.repository.StudentRepository;
import com.university.management.system.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final TeacherRepository teacherRepository;


    private static final Logger logger = LoggerFactory.getLogger(LessonService.class);

    public LessonService(LessonRepository lessonRepository, StudentService studentService, TeacherService teacherService, TeacherRepository teacherRepository) {
        this.lessonRepository = lessonRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.teacherRepository = teacherRepository;
    }



    public void addLesson(LessonDto lessonDto) {
        logger.info("Adding lesson: {}", lessonDto.lessonName());
        LessonEntity lessonEntity = lessonDto.toEntity();
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

    public void joinStudentToLesson (String studentId, String lessonId){
        logger.info("Joining student with ID: {} to lesson with ID: {}", studentId, lessonId);
        Optional<LessonEntity> lessonEntityOptional = lessonRepository.findById(lessonId);
        Optional<StudentEntity> joinStudentEntity = studentService.getStudentEntityById(studentId);
        lessonEntityOptional.ifPresent(lesson -> {
        joinStudentEntity.ifPresent(student ->{
            lesson.getStudent().add(student);
            lessonRepository.save(lesson);
            logger.info("Student joined lesson successfully: {}", lessonId);
        } );
        });
    }

    public void updateStudentLessonToNewLessonId (String lessonId, String newLessonId){
        logger.info("Updating student's lesson with ID: {} to new lesson with ID: {}", lessonId, newLessonId);
        Optional<LessonEntity> lessonEntityOptional = lessonRepository.findById(lessonId);
        Optional<LessonEntity> newLessonEntityOptional = lessonRepository.findById(newLessonId);
        lessonEntityOptional.ifPresent(lesson -> {
            newLessonEntityOptional.ifPresent(newLesson ->{
                lesson.getStudent().forEach(student -> {
                    student.getStudentLessonNames().remove(lesson);
                    newLesson.getStudent().add(student);
                });
                lessonRepository.save(lesson);
                lessonRepository.save(newLesson);
                logger.info("Student's lesson updated successfully: {}", lessonId);
            } );
        });
    }

    public void leaveStudentFromLesson (String studentId, String lessonId){
        logger.info("Leaving student with ID: {} from lesson with ID: {}", studentId, lessonId);
        Optional<LessonEntity> lessonEntityOptional = lessonRepository.findById(lessonId);
        Optional<StudentEntity> leaveStudentEntity = studentService.getStudentEntityById(studentId);
        lessonEntityOptional.ifPresent(lesson -> {
            leaveStudentEntity.ifPresent(student ->{
                lesson.getStudent().remove(student);
                lessonRepository.save(lesson);
                logger.info("Student left lesson successfully: {}", lessonId);
            } );
        });
    }

    public void changeTeacherOfLesson (String teacherId, String newTeacherId){
        logger.info("Changing teacher of lesson with ID: {} to new teacher with ID: {}", teacherId, newTeacherId);
        Optional<LessonEntity> lessonEntityOptional = lessonRepository.findById(teacherId);
        Optional<TeacherEntity> newTeacherEntityOptional = teacherRepository.findById(newTeacherId);
        lessonEntityOptional.ifPresent(lesson -> {
            newTeacherEntityOptional.ifPresent(newTeacher ->{
                lesson.setId(newTeacher.getTeacherId());
                lessonRepository.save(lesson);
                logger.info("Teacher of lesson updated successfully: {}", teacherId);
            } );
        });

    }

    public void deleteTeacherOfLesson (String teacherId,String lessonId){
     logger.info("Deleting teacher of lesson with ID: {} from lesson with ID: {}", teacherId, lessonId);
     Optional<LessonEntity> lessonEntityOptional = lessonRepository.findById(lessonId);
     Optional<TeacherEntity> teacherEntityOptional = teacherRepository.findById(teacherId);
     lessonEntityOptional.ifPresent(lesson -> {
         teacherEntityOptional.ifPresent(teacher ->{
             lesson.setTeacher(null);
             lessonRepository.save(lesson);
             logger.info("Teacher of lesson deleted successfully: {}", teacherId);
         } );
     });
    }

    public void getAllDetailsOfLesson (String lessonId){
        logger.info("Getting all details of lesson with ID: {}", lessonId);
        Optional<LessonEntity> lessonEntityOptional = lessonRepository.findById(lessonId);
        lessonEntityOptional.ifPresent(lesson ->{
            logger.info("Lesson Name: {}", lesson.getLessonName());
            logger.info("Teacher Name: {}",Optional.ofNullable(lesson.getTeacher()).map(TeacherEntity::getId));
            logger.info("Student Names: {}", lesson.getStudent().stream().map(StudentEntity::getStudentName).collect(Collectors.toList()));
        } );
    }


}

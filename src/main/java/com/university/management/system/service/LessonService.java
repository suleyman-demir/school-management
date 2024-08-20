package com.university.management.system.service;

import com.university.management.system.exception.ResourceNotFoundException;
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

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    private static final Logger logger = LoggerFactory.getLogger(LessonService.class);

    public LessonService(LessonRepository lessonRepository, StudentService studentService, TeacherService teacherService, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.lessonRepository = lessonRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;

    }

    public void addLesson(LessonDto lessonDto) {
        logger.info("Ders ekleniyor: {}", lessonDto.lessonName());
        LessonEntity lessonEntity = lessonDto.toEntity();
        lessonRepository.save(lessonEntity);
        logger.info("Ders başarıyla eklendi: {}", lessonEntity.getId());
    }

    public LessonDto getLessonByLessonId(String lessonId) {
        logger.info("ID'si {} olan ders aranıyor.", lessonId);
        LessonEntity lessonEntity = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip ders bulunamadı: " + lessonId));
        return LessonDto.convert(lessonEntity);
    }

    public void deleteLessonByLessonId(String lessonId) {
        logger.info("ID'si {} olan ders siliniyor.", lessonId);
        LessonEntity lessonEntity = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip ders bulunamadı: " + lessonId));
        lessonRepository.delete(lessonEntity);
        logger.info("Ders başarıyla silindi: {}", lessonId);
    }

    public void updateLessonByLessonId(String lessonId, String newLessonName) {
        logger.info("ID'si {} olan ders güncelleniyor.", lessonId);
        LessonEntity lessonEntity = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip ders bulunamadı: " + lessonId));
        lessonEntity.setLessonName(newLessonName);
        lessonRepository.save(lessonEntity);
        logger.info("Ders başarıyla güncellendi: {}", lessonEntity.getId());
    }

    public void joinStudentToLesson(String studentId, String lessonId) {
        logger.info("Öğrenci ID'si {} olan öğrenci, ders ID'si {} olan derse ekleniyor.", studentId, lessonId);
        LessonEntity lessonEntity = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip ders bulunamadı: " + lessonId));
        StudentEntity studentEntity = studentService.getStudentEntityById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip öğrenci bulunamadı: " + studentId));
        lessonEntity.getStudent().add(studentEntity);
        lessonRepository.save(lessonEntity);
        logger.info("Öğrenci başarıyla derse eklendi: {}", lessonId);
    }

    public void updateStudentLessonToNewLessonId(String lessonId, String newLessonId) {
        logger.info("Öğrencinin dersi ID'si {} olan dersten, ID'si {} olan yeni derse güncelleniyor.", lessonId, newLessonId);
        LessonEntity lessonEntity = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip ders bulunamadı: " + lessonId));
        LessonEntity newLessonEntity = lessonRepository.findById(newLessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip yeni ders bulunamadı: " + newLessonId));
        lessonEntity.getStudent().forEach(student -> {
            student.getStudentLessonNames().remove(lessonEntity);
            newLessonEntity.getStudent().add(student);
        });
        lessonRepository.save(lessonEntity);
        lessonRepository.save(newLessonEntity);
        logger.info("Öğrencinin dersi başarıyla güncellendi: {}", lessonId);
    }

    public void leaveStudentFromLesson(String studentId, String lessonId) {
        logger.info("Öğrenci ID'si {} olan öğrenci, ders ID'si {} olan dersten çıkarılıyor.", studentId, lessonId);
        LessonEntity lessonEntity = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip ders bulunamadı: " + lessonId));
        StudentEntity studentEntity = studentService.getStudentEntityById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip öğrenci bulunamadı: " + studentId));
        lessonEntity.getStudent().remove(studentEntity);
        lessonRepository.save(lessonEntity);
        logger.info("Öğrenci başarıyla dersten çıkarıldı: {}", lessonId);
    }

    public void changeTeacherOfLesson(String teacherId, String newTeacherId) {
        logger.info("ID'si {} olan dersin öğretmeni, ID'si {} olan yeni öğretmen ile değiştiriliyor.", teacherId, newTeacherId);
        LessonEntity lessonEntity = lessonRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip ders bulunamadı: " + teacherId));
        TeacherEntity newTeacherEntity = teacherRepository.findById(newTeacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip öğretmen bulunamadı: " + newTeacherId));
        lessonEntity.setId(newTeacherEntity.getTeacherId());
        lessonRepository.save(lessonEntity);
        logger.info("Dersin öğretmeni başarıyla değiştirildi: {}", teacherId);
    }

    public void deleteTeacherOfLesson(String teacherId, String lessonId) {
        logger.info("ID'si {} olan dersin öğretmeni, ID'si {} olan dersten siliniyor.", teacherId, lessonId);
        LessonEntity lessonEntity = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip ders bulunamadı: " + lessonId));
        TeacherEntity teacherEntity = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip öğretmen bulunamadı: " + teacherId));
        lessonEntity.setTeacher(null);
        lessonRepository.save(lessonEntity);
        logger.info("Dersin öğretmeni başarıyla silindi: {}", teacherId);
    }


    public LessonDto getAllDetailsOfLesson(String lessonId) {
        logger.info("ID'si {} olan dersin tüm detayları getiriliyor.", lessonId);
        LessonEntity lessonEntity = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Bu ID'ye sahip ders bulunamadı: " + lessonId));
        return LessonDto.convert(lessonEntity);
    }


}

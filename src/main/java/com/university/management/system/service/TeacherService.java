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
    public final TeacherRepository teacherRepository;
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherDto getTeacherByTeacherId(String teacherId) {

        logger.info("Requested Teacher : " + teacherId);
        Optional<TeacherEntity> teacherEntityOptional = teacherRepository.findByTeacherName(teacherId);
        return teacherEntityOptional.map(TeacherDto::convert).orElse(null);
    }


    public void addTeacher(TeacherDto teacherDto) {
        logger.info("Adding Teacher : " + teacherDto.name());
        teacherRepository.save(TeacherDto.convert(teacherDto));
    }

    public void updateTeacherByTeacherId(String teacherId,String newTeacherName) {
        logger.info("Updating Teacher : " + teacherId);
        teacherRepository.findById(teacherId).ifPresent(teacher -> {
            TeacherDto updatedTeacherDto = new TeacherDto(
                    teacher.getId(),
                    newTeacherName,
                    null

            );
            teacherRepository.save(TeacherDto.convert(updatedTeacherDto));
        });
    }
    public void deleteTeacherByTeacherId(String teacherId){
        logger.info("Deleting Teacher : " + teacherId);
        teacherRepository.deleteByTeacherId(teacherId);
    }


}

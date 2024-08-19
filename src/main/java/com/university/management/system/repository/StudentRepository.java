package com.university.management.system.repository;

import com.university.management.system.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity,String> {

    Optional<StudentEntity> findByStudentId(String studentId);
    void deleteByStudentId(String studentId);
    Optional<StudentEntity> getStudentByStudentId(String studentId);
}

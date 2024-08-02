package com.university.management.system.repository;

import com.university.management.system.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity,String> {

    Optional<StudentEntity> findByStudentName(String studentName);
    Optional<StudentEntity> findByStudentNumber(Integer studentNumber);

}

package com.StudentApplication.demo.StudentRepo;


import com.StudentApplication.demo.StudentEntity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepo extends MongoRepository<StudentEntity, String> {

        StudentEntity findByStudentId(String studentId);
        StudentEntity deleteByStudentId(String studentId);
        StudentEntity findByStudentRegNo(String studentRegNo);

        List<StudentEntity> findAll();
}
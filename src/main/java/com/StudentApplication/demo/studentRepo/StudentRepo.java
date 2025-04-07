package com.StudentApplication.demo.studentRepo;


import com.StudentApplication.demo.studentEntity.StudentEntity;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@NonNullApi
@Repository
public interface StudentRepo extends MongoRepository<StudentEntity, String> {

        StudentEntity findByStudentId(String studentId);
        void deleteByStudentId(String studentId);
        List<StudentEntity> findAll();
}
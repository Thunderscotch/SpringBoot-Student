package com.StudentApplication.demo.StudentServices;


import com.StudentApplication.demo.StudentDTO.StudentDto;
import com.StudentApplication.demo.StudentEntity.StudentEntity;
import com.StudentApplication.demo.StudentRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public StudentDto addStudent(StudentDto studentDto) {
        StudentEntity studentEntity = studentRepo.save(StudentEntity.builder().studentId(studentDto.getStudentId()).studentRegNo(studentDto.getStudentRegNo()).studentBranch(studentDto.getStudentBranch()).studentDepartment(studentDto.getStudentDepartment()).studentFirstName(studentDto.getStudentFirstName()).studentLastName(studentDto.getStudentLastName()).studentEmail(studentDto.getStudentEmail()).studentPhone(studentDto.getStudentPhone()).build());
        return StudentDto.builder().studentId(studentEntity.getStudentId()).studentRegNo(studentEntity.getStudentRegNo()).studentFirstName(studentEntity.getStudentFirstName()).studentLastName(studentEntity.getStudentLastName()).studentPhone(studentEntity.getStudentPhone()).studentDepartment(studentEntity.getStudentDepartment()).studentEmail(studentEntity.getStudentEmail()).studentBranch(studentEntity.getStudentBranch()).build();
    }

    public StudentDto getStudent(String studentId){
        StudentEntity studentEntity = studentRepo.findByStudentId(studentId);
        return StudentDto.builder().studentRegNo(studentEntity.getStudentRegNo()).studentFirstName(studentEntity.getStudentFirstName()).studentLastName(studentEntity.getStudentLastName()).studentBranch(studentEntity.getStudentBranch()).studentDepartment(studentEntity.getStudentDepartment()).studentEmail(studentEntity.getStudentEmail()).studentPhone(studentEntity.getStudentPhone()).build();
    }

    public StudentDto updateStudentDetails(String studentId, StudentDto studentDto) {
        Optional<StudentEntity> studentEntity = Optional.ofNullable(studentRepo.findByStudentId(studentId));

        if (studentEntity.isPresent()){
            StudentEntity existingStudent = studentEntity.get();

            existingStudent.setStudentRegNo(studentDto.getStudentRegNo());
            existingStudent.setStudentFirstName(studentDto.getStudentFirstName());
            existingStudent.setStudentLastName(studentDto.getStudentLastName());
            existingStudent.setStudentBranch(studentDto.getStudentBranch());
            existingStudent.setStudentEmail(studentDto.getStudentEmail());
            existingStudent.setStudentDepartment(studentDto.getStudentDepartment());
            existingStudent.setStudentPhone(studentDto.getStudentPhone());

            StudentEntity updatedStudents = studentRepo.save(existingStudent);

            return StudentDto.builder().studentRegNo(updatedStudents.getStudentRegNo()).studentFirstName(updatedStudents.getStudentFirstName()).studentLastName(updatedStudents.getStudentLastName()).studentBranch(updatedStudents.getStudentBranch()).studentDepartment(updatedStudents.getStudentDepartment()).studentEmail(updatedStudents.getStudentEmail()).studentPhone(updatedStudents.getStudentPhone()).build();
        } else {
            throw new RuntimeException("student not found");
        }
    }


    public void deleteStudentDetails(String studentId) {
        StudentEntity studentEntity = studentRepo.deleteByStudentId(studentId);
    }

    public List<StudentDto> getAllStudent() {
        List<StudentEntity> studentEntity = studentRepo.findAll();
        return studentEntity.stream().map(studentEntity1 -> StudentDto.builder()
                .studentRegNo(studentEntity1.getStudentRegNo())
                .studentFirstName(studentEntity1.getStudentFirstName())
                .studentLastName(studentEntity1.getStudentLastName())
                .studentPhone(studentEntity1.getStudentPhone())
                .studentEmail(studentEntity1.getStudentEmail())
                .studentDepartment(studentEntity1.getStudentDepartment())
                .studentBranch(studentEntity1.getStudentBranch()).build()).collect(Collectors.toList());
    }
}

package com.StudentApplication.demo.controller;
import java.util.List;
import com.StudentApplication.demo.StudentDTO.StudentDto;
import com.StudentApplication.demo.StudentServices.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/students")
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public StudentDto addStudent (@RequestBody StudentDto studentDto){
        System.out.println("added");
        return studentService.addStudent(studentDto);
    }

    @GetMapping("/all")
    public List<StudentDto> getAllStudents(){
        return studentService.getAllStudent();
    }

    @GetMapping("/get{id}")
    public StudentDto getStudent (@PathVariable String studentId ){
        return studentService.getStudent(studentId);
    }

    @PutMapping("/update{id}")
    public void updateStudentDetails(@PathVariable String studentId, @RequestBody StudentDto studentDto){
        StudentDto studentDto1 = studentService.updateStudentDetails(studentId, studentDto);
        System.out.println("Updated Details" + studentDto1);
    }

    @DeleteMapping("/delete{id}")
    public void deleteStudentDetails(@PathVariable String studentId){
        studentService.deleteStudentDetails(studentId);
    }
}

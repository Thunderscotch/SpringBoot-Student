package com.StudentApplication.demo.studentEntity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "STUDENT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentEntity {
    String studentId;
    String studentRegNo;
    String studentFirstName;
    String studentLastName;
    String studentEmail;
    String studentPhone;
    String studentDepartment;
    String studentBranch;

}

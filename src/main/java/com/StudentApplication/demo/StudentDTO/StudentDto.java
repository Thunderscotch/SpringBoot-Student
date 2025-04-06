package com.StudentApplication.demo.StudentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    String studentId;
    String studentRegNo;
    String studentFirstName;
    String studentLastName;
    String studentEmail;
    String studentPhone;
    String studentDepartment;
    String studentBranch;

}

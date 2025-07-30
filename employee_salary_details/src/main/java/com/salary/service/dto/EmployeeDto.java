package com.salary.service.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDto {

    private String id;
    private String employeeName;
    private String employeeLastName;
    private String gmailId;
    private String password;
    private long mobileNumber;
    private String gender;
    private String address;
    private Date joiningDate;
    private String department;
    private String designation;
    private String exitDate;
    private String role;
    private boolean isActive;
}

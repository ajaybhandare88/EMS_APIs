package com.employee.service.entities;

import com.employee.service.Dto.AttendanceDto;
import com.employee.service.Dto.BankAccountDto;
import com.employee.service.Dto.EmployeeSalaryDetailsDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    private String id;
    @Column(name = "EMP_NAME",length = 15)
    private String employeeName;
    @Column(name = "EMP_LAST_NAME",length = 20)
    private String employeeLastName;
    @Column(name = "GMAIL_ID")
    private String gmailId;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "MOBILE_NUMBER",length = 10)
    private long mobileNumber;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "JOINING_DATE")
    private Date joiningDate;
    @Column(name = "DEPARTMENT")
    private String department;
    @Column(name = "DESIGNATION")
    private String designation;
    @Column(name = "EXIT_DATE")
    private String exitDate;
    @Column(name = "ROLE")
    private String role;
    @Column(name = "ACCOUNT_MODE")
    private boolean isActive;

    @Transient
    private BankAccountDto bankAccountDto;

    @Transient
    private EmployeeSalaryDetailsDto employeeSalaryDetailsDto;

}

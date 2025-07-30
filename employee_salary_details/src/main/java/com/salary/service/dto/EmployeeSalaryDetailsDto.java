package com.salary.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalaryDetailsDto {

    private int id;
    private long basicSalary;
    private long medicalAllowance;
    private long HouseRentAllAllowance;
    private long persnolAllowance;
    private long transportingAllowance;
    private long insurance;
    private long professionalTax;
    private String employeeId;

}

package com.employee.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalaryDetailsDto {

    private long basicSalary;
    private long medicalAllowance;
    private long HouseRentAllAllowance;
    private long persnolAllowance;
    private long transportingAllowance;
    private long insurance;
    private long professionalTax;
}

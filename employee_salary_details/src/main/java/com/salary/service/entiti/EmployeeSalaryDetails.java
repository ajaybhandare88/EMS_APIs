package com.salary.service.entiti;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeSalaryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

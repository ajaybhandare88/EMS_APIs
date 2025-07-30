package com.employee.service.external.services;

import com.employee.service.Dto.EmployeeSalaryDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EMPLOYEE-SALARY-SERVICE")
public interface SalaryService {

    @GetMapping("salary/employeeId/{employeeId}")
    EmployeeSalaryDetailsDto getEmployeeSalaryDetails(@PathVariable("employeeId") String employeeId);

}

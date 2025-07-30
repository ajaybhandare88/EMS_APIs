package com.employee.attendance.external.services;

import com.employee.attendance.Dto.EmployeeDto;
import com.employee.attendance.exception.ResourceNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "employee-service")
public interface EmployeeServices {

    @GetMapping("/employee/id/{id}")
    EmployeeDto getEmployeeById(@PathVariable("id") String id);

}

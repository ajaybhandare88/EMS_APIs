package com.salary.service.controller;

import com.salary.service.dto.EmployeeSalaryDetailsDto;
import com.salary.service.exception.EmptyException;
import com.salary.service.exception.ResourceNotFoundException;
import com.salary.service.services.EmployeeSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/salary")
public class SalaryServiceController {

    @Autowired
    private EmployeeSalaryService employeeSalaryService;

    //  Create new employee Salary details
    @PostMapping("/employeeId/{employeeId}")
    ResponseEntity<EmployeeSalaryDetailsDto> createSalaryDetails(@PathVariable("employeeId") String employeeId, @RequestBody EmployeeSalaryDetailsDto employeeSalaryDetailsDto)throws ResourceNotFoundException
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeSalaryService.createSalaryDetails(employeeId,employeeSalaryDetailsDto));
    }

    //  Get employee Salary details
    @GetMapping("/employeeId/{employeeId}")
    EmployeeSalaryDetailsDto getEmployeeSalaryDetails(@PathVariable("employeeId") String employeeId)throws ResourceNotFoundException, EmptyException
    {
        return employeeSalaryService.getEmployeeSalaryDetails(employeeId);
    }

    //  Update employee Salary details
    @PutMapping("/employeeId/{employeeId}")
    ResponseEntity<EmployeeSalaryDetailsDto> updateEmployeeSalaryDetails(@PathVariable("employeeId") String employeeId, @RequestBody EmployeeSalaryDetailsDto employeeSalaryDetailsDto)throws ResourceNotFoundException,EmptyException
    {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeSalaryService.updateEmployeeSalaryDetails(employeeId,employeeSalaryDetailsDto));
    }

    //  Delete employee Salary details
    @DeleteMapping("/employeeId/{employeeId}")
    ResponseEntity<Map<String, String>> deleteEmployeeSalaryDetails(@PathVariable("employeeId") String employeeId)throws ResourceNotFoundException,EmptyException
    {
        boolean result=employeeSalaryService.deleteEmployeeSalaryDetails(employeeId);
        Map<String,String> map=new HashMap<>();
        if (result)
        {
            map.put("Success","Employee salary details deleted");
            map.put("Employee Id",employeeId);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        map.put("Failed","Could not delete salary details");
        map.put("Employee Id",employeeId);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }


}

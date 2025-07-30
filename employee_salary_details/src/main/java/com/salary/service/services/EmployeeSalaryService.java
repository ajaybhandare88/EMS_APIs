package com.salary.service.services;
import com.salary.service.dto.EmployeeSalaryDetailsDto;
import com.salary.service.exception.EmptyException;
import com.salary.service.exception.ResourceNotFoundException;

public interface EmployeeSalaryService {

//  Create new employee Salary details
    EmployeeSalaryDetailsDto createSalaryDetails(String employeeId, EmployeeSalaryDetailsDto employeeSalaryDetailsDto)throws ResourceNotFoundException;

//  Get employee Salary details
    EmployeeSalaryDetailsDto getEmployeeSalaryDetails(String employeeId)throws ResourceNotFoundException, EmptyException;

//  Update employee Salary details
    EmployeeSalaryDetailsDto updateEmployeeSalaryDetails(String employeeId, EmployeeSalaryDetailsDto employeeSalaryDetailsDto)throws ResourceNotFoundException,EmptyException;

//  Delete employee Salary details
    boolean deleteEmployeeSalaryDetails(String employeeId)throws ResourceNotFoundException,EmptyException;
}

package com.employee.service.services;

import com.employee.service.Dto.AttendanceDto;
import com.employee.service.Dto.EmployeeDto;
import com.employee.service.entities.Employee;
import com.employee.service.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeServices {

//  Create new employee
    Employee saveEmployee(EmployeeDto employeeDto);

//  Find all employee
    List<EmployeeDto> getAllEmployees()throws ResourceNotFoundException;

//  Find employee by employee id
    EmployeeDto findByEmployeeId(String id)throws ResourceNotFoundException;

    EmployeeDto onlyFindByEmployeeId(String id)throws ResourceNotFoundException;

//  Find employee by employee name
    List<EmployeeDto> findByEmployeeName(String name)throws ResourceNotFoundException;

//  Update employee details
    EmployeeDto updateEmployeeDetails(EmployeeDto details,String employeeId)throws ResourceNotFoundException;

//  Update employee details by admin
    EmployeeDto updateEmployeeDetailsByAdmin(EmployeeDto details)throws ResourceNotFoundException;

//  Delete employee from database
    boolean deleteEmployee(String id)throws ResourceNotFoundException;


}

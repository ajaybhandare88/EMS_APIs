package com.employee.service.controller;

import com.employee.service.Dto.EmployeeDto;
import com.employee.service.entities.Employee;
import com.employee.service.exception.ResourceNotFoundException;
import com.employee.service.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto employeeDto)
    {
       Employee employee=employeeServices.saveEmployee(employeeDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployee() throws ResourceNotFoundException {
        List<EmployeeDto> empolyeeList=employeeServices.getAllEmployees();
           return ResponseEntity.status(HttpStatus.FOUND).body(empolyeeList);
    }


    @GetMapping("/id/{id}")
    public EmployeeDto getEmployeeById(@PathVariable("id") String id) throws ResourceNotFoundException {
        EmployeeDto empolyee=employeeServices.findByEmployeeId(id);
        return empolyee;
    }

    @GetMapping("/{id}")
    public EmployeeDto onlyGetEmployeeById(@PathVariable("id") String id) throws ResourceNotFoundException {
        EmployeeDto empolyee=employeeServices.onlyFindByEmployeeId(id);
        return empolyee;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByName(@PathVariable("name") String name) throws ResourceNotFoundException {
        List<EmployeeDto> empolyeeList=employeeServices.findByEmployeeName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(empolyeeList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteEmployee(@PathVariable("id") String id)throws ResourceNotFoundException
    {
        boolean result=employeeServices.deleteEmployee(id);
        Map<String,String> map=new HashMap<>();
        if (result)
        {
            map.put("Success","Employee delete successfully");
            map.put("Employee Id",id.toString());
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        map.put("Failed","Could not delete employee");
        map.put("Employee Id",id.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

}

package com.employee.service.impl;

import com.employee.service.Dto.AttendanceDto;
import com.employee.service.Dto.EmployeeDto;
import com.employee.service.entities.Employee;
import com.employee.service.exception.ResourceNotFoundException;
import com.employee.service.external.services.BankService;
import com.employee.service.external.services.SalaryService;
import com.employee.service.repositories.EmployeeRepo;
import com.employee.service.services.EmployeeServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServicesImpl implements EmployeeServices {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private BankService bankService;

    @Autowired
    private SalaryService salaryService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {
        String randomEmployeeId= UUID.randomUUID().toString();
        employeeDto.setId(randomEmployeeId);
        return employeeRepo.save(employeeDtoToEmployee(employeeDto));
    }


    @Override
    public List<EmployeeDto> getAllEmployees()throws ResourceNotFoundException {

        List<Employee> listOfEmployee=employeeRepo.findAll();

        if (listOfEmployee.isEmpty())
        {
            throw new ResourceNotFoundException();
        }
        List<EmployeeDto> employeeDtos=new ArrayList<>();
        listOfEmployee.forEach((e)->{
            employeeDtos.add(employeeToEmployeeDto(e));
        });
        return employeeDtos;

    }


    @Override
    public EmployeeDto findByEmployeeId(String id)throws ResourceNotFoundException{

        Optional<Employee> emp=employeeRepo.findById(id);
        if (emp.isEmpty())
        {
            throw new ResourceNotFoundException("Employee","id",id);
        }

        try {
            emp.get().setEmployeeSalaryDetailsDto(salaryService.getEmployeeSalaryDetails(id));
        }catch (Exception e)
        {
            System.out.println(e.getMessage());;
        }


        try {
            emp.get().setBankAccountDto(bankService.getBankAccount(id));
            return employeeToEmployeeDto(emp.get());
        }catch (Exception e)
        {
            return employeeToEmployeeDto(emp.get());
        }

    }

    @Override
    public EmployeeDto onlyFindByEmployeeId(String id) throws ResourceNotFoundException {
        Optional<Employee> emp=employeeRepo.findById(id);
        if (emp.isEmpty())
        {
            throw new ResourceNotFoundException("Employee","id",id);
        }
        return employeeToEmployeeDto(emp.get());
    }


    @Override
    public List<EmployeeDto> findByEmployeeName(String name) throws ResourceNotFoundException{
        List<Employee> listOfEmployee=employeeRepo.findByEmployeeName(name);
        if (listOfEmployee.isEmpty())
        {
            throw new ResourceNotFoundException("Employee","name",name);
        }

        List<EmployeeDto> employeeDtos=new ArrayList<>();
        listOfEmployee.forEach((e)->{
            employeeDtos.add(employeeToEmployeeDto(e));
        });
        return employeeDtos;

    }




//  This service soon
    @Override
    public EmployeeDto updateEmployeeDetails(EmployeeDto details,String employeeId) {

        Optional<Employee> employee=employeeRepo.findById(employeeId);
        return null;
    }


//  This service soon
    @Override
    public EmployeeDto updateEmployeeDetailsByAdmin(EmployeeDto details) {
        return null;
    }



    @Override
    public boolean deleteEmployee(String id) throws ResourceNotFoundException{
        Optional<Employee> employee=employeeRepo.findById(id);

        if (employee.isEmpty())
        {
            throw new ResourceNotFoundException("Employee","id",id);
        }
        employeeRepo.delete(employee.get());
        return true;
    }



//    Convert ENTITIES TO DTO

    public EmployeeDto employeeToEmployeeDto(Employee employee)
    {
        EmployeeDto emp=modelMapper.map(employee,EmployeeDto.class);
        return emp;
    }


//    Convert  DTO TO ENTITIES
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto)
    {
        Employee emp=modelMapper.map(employeeDto,Employee.class);
        return emp;
    }

}

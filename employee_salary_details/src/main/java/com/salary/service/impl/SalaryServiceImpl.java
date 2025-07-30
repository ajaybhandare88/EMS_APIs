package com.salary.service.impl;

import com.salary.service.dto.EmployeeDto;
import com.salary.service.dto.EmployeeSalaryDetailsDto;
import com.salary.service.entiti.EmployeeSalaryDetails;
import com.salary.service.exception.EmptyException;
import com.salary.service.exception.ResourceNotFoundException;
import com.salary.service.external.EmployeeService;
import com.salary.service.salaryServiceRepositories.SalaryDetailsRepo;
import com.salary.service.services.EmployeeSalaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements EmployeeSalaryService {

    @Autowired
    private SalaryDetailsRepo salaryDetailsRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public EmployeeSalaryDetailsDto createSalaryDetails(String employeeId, EmployeeSalaryDetailsDto employeeSalaryDetailsDto) throws ResourceNotFoundException {

        try {
            EmployeeDto employee=employeeService.getEmployeeById(employeeId);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        EmployeeSalaryDetails result=salaryDetailsRepo.findByEmployeeId(employeeId);
        if (result!=null)
        {
            throw new ResourceNotFoundException("This employee has salary details ! You can't again add salary details ");
        }
        employeeSalaryDetailsDto.setEmployeeId(employeeId);
        return entityToDto(salaryDetailsRepo.save(dtoToEntity(employeeSalaryDetailsDto)));



    }

    @Override
    public EmployeeSalaryDetailsDto getEmployeeSalaryDetails(String employeeId) throws ResourceNotFoundException, EmptyException {

        try {
            EmployeeDto employee=employeeService.getEmployeeById(employeeId);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        EmployeeSalaryDetails result=salaryDetailsRepo.findByEmployeeId(employeeId);
        if (result==null)
        {
            throw new EmptyException(employeeId);
        }
        return entityToDto(salaryDetailsRepo.findByEmployeeId(employeeId));

    }

    @Override
    public EmployeeSalaryDetailsDto updateEmployeeSalaryDetails(String employeeId, EmployeeSalaryDetailsDto employeeSalaryDetailsDto) throws ResourceNotFoundException, EmptyException {

        try {
            EmployeeDto employee=employeeService.getEmployeeById(employeeId);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        EmployeeSalaryDetails result=salaryDetailsRepo.findByEmployeeId(employeeId);
        if (result==null)
        {
            throw new EmptyException(employeeId);
        }
        result.setBasicSalary(employeeSalaryDetailsDto.getBasicSalary());
        result.setInsurance(employeeSalaryDetailsDto.getInsurance());
        result.setMedicalAllowance(employeeSalaryDetailsDto.getMedicalAllowance());
        result.setHouseRentAllAllowance(employeeSalaryDetailsDto.getHouseRentAllAllowance());
        result.setPersnolAllowance(employeeSalaryDetailsDto.getPersnolAllowance());
        result.setTransportingAllowance(employeeSalaryDetailsDto.getTransportingAllowance());
        result.setProfessionalTax(employeeSalaryDetailsDto.getProfessionalTax());
        return entityToDto(salaryDetailsRepo.save(result));
    }

    @Override
    public boolean deleteEmployeeSalaryDetails(String employeeId) throws ResourceNotFoundException, EmptyException {

        try {
            EmployeeDto employee=employeeService.getEmployeeById(employeeId);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        EmployeeSalaryDetails result=salaryDetailsRepo.findByEmployeeId(employeeId);
        if (result==null)
        {
            throw new EmptyException(employeeId);
        }
        salaryDetailsRepo.delete(result);
        return true;
    }

    public EmployeeSalaryDetailsDto entityToDto(EmployeeSalaryDetails employeeSalaryDetails)
    {
        return modelMapper.map(employeeSalaryDetails,EmployeeSalaryDetailsDto.class);
    }

    public EmployeeSalaryDetails dtoToEntity(EmployeeSalaryDetailsDto employeeSalaryDetailsDto)
    {
        return modelMapper.map(employeeSalaryDetailsDto,EmployeeSalaryDetails.class);
    }

}

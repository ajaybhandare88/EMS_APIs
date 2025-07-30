package com.bank.details.service.impl;

import com.bank.details.service.dto.BankAccountDto;
import com.bank.details.service.dto.EmployeeDto;
import com.bank.details.service.entities.BankAccount;
import com.bank.details.service.exception.EmptyException;
import com.bank.details.service.exception.ResourceNotFoundException;
import com.bank.details.service.external.externalService.EmployeeService;
import com.bank.details.service.repositorie.BankRepo;
import com.bank.details.service.services.BankService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankService {

    @Autowired
    private ModelMapper modelMappper;

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public BankAccountDto createBankAccount(String employeeId, BankAccount bankAccount) throws ResourceNotFoundException {

        try {
            EmployeeDto employee=employeeService.getEmployeeById(employeeId);

        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        BankAccount result=bankRepo.findByEmployeeId(employeeId);
        if (result!=null)
        {
            throw new ResourceNotFoundException("Bank account already created for this person");
        }
        bankAccount.setEmployeeId(employeeId);
        return entitiToDto(bankRepo.save(bankAccount));
    }

    @Override
    public BankAccountDto getBankAccountDetails(String employeeId)throws ResourceNotFoundException,EmptyException{

        try {
            EmployeeDto employee=employeeService.getEmployeeById(employeeId);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        BankAccount bankAccountDetails=bankRepo.findByEmployeeId(employeeId);
        if (bankAccountDetails==null)
        {
            throw new EmptyException(employeeId);
        }
        return entitiToDto(bankAccountDetails);
    }

    @Override
    public BankAccountDto updateBankAccountDetails(String employeeId, BankAccountDto bankAccountDto) throws ResourceNotFoundException, EmptyException {

        try {
            EmployeeDto employee=employeeService.getEmployeeById(employeeId);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        BankAccount bankAccount=bankRepo.findByEmployeeId(employeeId);

        if (bankAccount==null)
        {
            throw new EmptyException(employeeId);
        }else
        {
            bankAccount.setBankAccountNumber(bankAccountDto.getBankAccountNumber());
            bankAccount.setBankName(bankAccountDto.getBankName());
            bankAccount.setBankAddress(bankAccountDto.getBankAddress());
            bankAccount.setBranchName(bankAccountDto.getBranchName());
            bankAccount.setIFSC_Code(bankAccountDto.getIFSC_Code());
            BankAccount save = bankRepo.save(bankAccount);
            return entitiToDto(save);
        }


    }

    @Override
    public boolean deleteBankAccountDetails(String employeeId) throws ResourceNotFoundException, EmptyException {

        try {
            EmployeeDto employee=employeeService.getEmployeeById(employeeId);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        BankAccount bankAccount=bankRepo.findByEmployeeId(employeeId);

        if (bankAccount==null)
        {
            throw new EmptyException(employeeId);
        }else
        {
            bankRepo.delete(bankAccount);
            return true;
        }

    }

    public BankAccountDto entitiToDto(BankAccount BankAccount)
    {
        BankAccountDto bankAccountDto=modelMappper.map(BankAccount,BankAccountDto.class);
        return bankAccountDto;
    }

    public BankAccount dtoToEntiti(BankAccountDto bankAccountDto)
    {
        BankAccount bankAccount=modelMappper.map(bankAccountDto,BankAccount.class);
        return bankAccount;
    }
}

package com.bank.details.service.services;

import com.bank.details.service.dto.BankAccountDto;
import com.bank.details.service.entities.BankAccount;
import com.bank.details.service.exception.EmptyException;
import com.bank.details.service.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;

public interface BankService {



//  Create new bank account
    BankAccountDto createBankAccount(String employeeId,BankAccount bankAccount)throws ResourceNotFoundException;

//  Get bank account details
    BankAccountDto getBankAccountDetails(String employeeId)throws ResourceNotFoundException, EmptyException;

//  Update bank account details
    BankAccountDto updateBankAccountDetails(String employeeId, BankAccountDto bankAccountDto)throws ResourceNotFoundException,EmptyException;

//  Delete bank account details
    boolean deleteBankAccountDetails(String employeeId)throws ResourceNotFoundException,EmptyException;
}

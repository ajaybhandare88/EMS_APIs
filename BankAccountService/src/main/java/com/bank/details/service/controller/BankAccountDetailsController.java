package com.bank.details.service.controller;

import com.bank.details.service.dto.BankAccountDto;
import com.bank.details.service.entities.BankAccount;
import com.bank.details.service.exception.EmptyException;
import com.bank.details.service.exception.ResourceNotFoundException;
import com.bank.details.service.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountDetailsController {

    @Autowired
    private BankService bankService;

    //  Create new bank account
    @PostMapping("/employeeId/{employeeId}")
    ResponseEntity<BankAccountDto> createBankAccount(@PathVariable("employeeId") String employeeId, @RequestBody BankAccount bankAccount)throws ResourceNotFoundException
    {
        BankAccountDto bankAccountDto=bankService.createBankAccount(employeeId,bankAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountDto);
    };

    //  Get bank account details
    @GetMapping("/employeeId/{employeeId}")
    BankAccountDto getBankAccountDetails(@PathVariable("employeeId") String employeeId)throws ResourceNotFoundException, EmptyException
    {
        return bankService.getBankAccountDetails(employeeId);
    };

    //  Update bank account details
    @PutMapping("/employeeId/{employeeId}")
    ResponseEntity<BankAccountDto> updateBankAccountDetails(@PathVariable("employeeId")String employeeId,@RequestBody BankAccountDto bankAccountDto)throws ResourceNotFoundException,EmptyException
    {
        return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(bankService.updateBankAccountDetails(employeeId,bankAccountDto));
    };

    //  Delete bank account details
    @DeleteMapping("/employeeId/{employeeId}")
    ResponseEntity<Map<String, String>> deleteBankAccountDetails(@PathVariable("employeeId")String employeeId)throws ResourceNotFoundException,EmptyException
    {
        boolean result=bankService.deleteBankAccountDetails(employeeId);
        Map<String,String> map=new HashMap<>();
        if (result)
        {
            map.put("Success","Bank account deleted");
            map.put("Employee Id",employeeId);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        map.put("Failed","Could not delete bank account");
        map.put("Employee Id",employeeId);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);

    };



}

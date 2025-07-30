package com.employee.service.external.services;

import com.employee.service.Dto.BankAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "BANKACCOUNT-SERVICE")
public interface BankService {

    @GetMapping("/bankAccount/employeeId/{id}")
    public BankAccountDto getBankAccount(@PathVariable("id") String id);

}

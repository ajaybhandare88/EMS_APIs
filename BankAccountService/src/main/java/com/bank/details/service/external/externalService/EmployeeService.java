package com.bank.details.service.external.externalService;

import com.bank.details.service.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service")
public interface EmployeeService {


    @GetMapping("/employee/{id}")
    EmployeeDto getEmployeeById(@PathVariable("id") String id);

}

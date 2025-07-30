package com.employee.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDto {

    private long bankAccountNumber;
    private String bankName;
    private String IFSC_Code;
    private String branchName;
    private String bankAddress;

}

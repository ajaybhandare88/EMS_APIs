package com.bank.details.service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BankAccountDto {

    private int id;
    private long bankAccountNumber;
    private String bankName;
    private String IFSC_Code;
    private String branchName;
    private String bankAddress;

}

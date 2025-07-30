package com.bank.details.service.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "ACCOUNT NO")
    private long bankAccountNumber;
    @Column(name = "BANK NAME")
    private String bankName;
    @Column(name = "IFSC CODE")
    private String IFSC_Code;
    @Column(name = "BRANCH_NAME")
    private String branchName;
    @Column(name = "BANK ADD")
    private String bankAddress;
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

}

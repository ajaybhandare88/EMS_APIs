package com.bank.details.service.repositorie;

import com.bank.details.service.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepo extends JpaRepository<BankAccount,Integer> {

    BankAccount findByEmployeeId(String employeeId);

}

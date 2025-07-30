package com.bank.details.service.exception;

import java.time.LocalDate;

public class EmptyException extends Exception{

    private String employeeId;



    public EmptyException(String employeeId) {
        super(" ⚠️ "+employeeId+" Don't have bank account details");
        this.employeeId = employeeId;
    }



}

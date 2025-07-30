package com.salary.service.exception;

public class EmptyException extends Exception{

    private String employeeId;



    public EmptyException(String employeeId) {
        super(" ⚠️ "+employeeId+" Don't have salary details");
        this.employeeId = employeeId;
    }



}

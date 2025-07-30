package com.employee.attendance.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OutPunchingException extends Exception{

    private String sms;
    private String employeeId;
    private LocalDate date;

    public OutPunchingException(String sms, LocalDate date, String employeeId) {
        super(sms);
        this.sms = sms;
        this.date = date;
        this.employeeId = employeeId;
    }
}

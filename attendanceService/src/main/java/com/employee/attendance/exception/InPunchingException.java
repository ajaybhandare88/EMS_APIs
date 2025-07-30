package com.employee.attendance.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class InPunchingException extends Exception{

    private String employeeId;
    private String attendance;
    private LocalDate date;
    private LocalTime inTime;
    private LocalTime outTime;

    public InPunchingException(String employeeId, LocalDate date, String attendance, LocalTime inTime, LocalTime outTime) {
        super("You already punching on today");
        this.attendance = attendance;
        this.date = date;
        this.employeeId = employeeId;
        this.inTime=inTime;
        this.outTime=outTime;

    }

}

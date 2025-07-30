package com.employee.attendance.exception;

import java.time.LocalDate;

public class EmptyException extends Exception{

    private String content;
    private String status;
    private LocalDate date;
    private String employeeId;
    private String attendance;
    private LocalDate toDate;
    private LocalDate fromeDate;


    public EmptyException(String sms) {
        super(sms);
    }

    public EmptyException(String content, LocalDate date) {
        super("⚠️ There are no "+ content+" on " + date+ " this date");
        this.content = content;
        this.date = date;
    }

    public EmptyException(String content, LocalDate fromeDate,LocalDate toDate) {
        super("⚠️ "+ content+" not available on " + fromeDate+ " to "+toDate);
        this.fromeDate = fromeDate;
        this.toDate = toDate;
    }


}

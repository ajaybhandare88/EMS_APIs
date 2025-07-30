package com.employee.attendance.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {

    private int id;
    private LocalDate date;
    private LocalTime inTime;
    private LocalTime outTime;
    private String status;
    private boolean halfDay;
    private boolean fullDay;
    private String employeeId;

}

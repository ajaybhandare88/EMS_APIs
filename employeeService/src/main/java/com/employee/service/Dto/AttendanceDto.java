package com.employee.service.Dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

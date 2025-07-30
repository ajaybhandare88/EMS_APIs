package com.employee.attendance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate date;
    private LocalTime inTime;
    private LocalTime outTime;
    private String status;
    private boolean halfDay;
    private boolean fullDay;
    private String employeeId;

    public Attendance(LocalDate date, LocalTime inTime, LocalTime outTime, String status, String employeeId) {
        this.date = date;
        this.inTime = inTime;
        this.outTime = outTime;
        this.status = status;
        this.employeeId = employeeId;
    }
}

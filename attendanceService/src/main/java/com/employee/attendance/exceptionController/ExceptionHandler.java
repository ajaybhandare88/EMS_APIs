package com.employee.attendance.exceptionController;

import com.employee.attendance.exception.EmptyException;
import com.employee.attendance.exception.InPunchingException;
import com.employee.attendance.exception.OutPunchingException;
import com.employee.attendance.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> notFoundException(ResourceNotFoundException ex)
    {
        Map<String,String> map=new HashMap<>();
        map.put("Message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyException.class)
    public ResponseEntity<Map<String,String>> emptyException(EmptyException ex)
    {
        Map<String,String> map=new HashMap<>();
        map.put("Message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InPunchingException.class)
    public ResponseEntity<Map<String,String>> inPunchingExceptions(InPunchingException ex)
    {
        Map<String,String> map=new HashMap<>();
        map.put("Message",ex.getMessage());
        map.put("Employee Id",ex.getEmployeeId());
        map.put("Date",ex.getDate().toString());
        map.put("status",ex.getAttendance());
        map.put("Time",ex.getInTime().toString());
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(map);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(OutPunchingException.class)
    public ResponseEntity<Map<String,String>> outPunchingExceptions(OutPunchingException ex)
    {
        Map<String,String> map=new HashMap<>();
        map.put("Message",ex.getMessage());
        map.put("Employee Id",ex.getEmployeeId());
        map.put("Date",ex.getDate().toString());
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
    }

}

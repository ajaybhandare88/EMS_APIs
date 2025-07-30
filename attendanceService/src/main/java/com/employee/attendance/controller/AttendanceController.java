package com.employee.attendance.controller;

import com.employee.attendance.Dto.AttendanceDto;
import com.employee.attendance.exception.EmptyException;
import com.employee.attendance.exception.InPunchingException;
import com.employee.attendance.exception.OutPunchingException;
import com.employee.attendance.exception.ResourceNotFoundException;
import com.employee.attendance.services.AttendanceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceServices attendanceServices;


    @PostMapping("/InPunch/{employeeId}")
    public ResponseEntity<Map<String,String>> inpunch(@PathVariable("employeeId")String employeeId)throws InPunchingException, ResourceNotFoundException
    {
        AttendanceDto result=attendanceServices.inPunch(employeeId);

            Map<String,String> map=new HashMap<>();
            map.put("Employee Id",result.getEmployeeId());
            map.put("Status","Done");
            map.put("Date",result.getDate().toString());
            map.put("Time",result.getInTime().toString());

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
    }

    @PostMapping("/OutPunch/{employeeId}")
    public ResponseEntity<Map<String,String>> outpunch(@PathVariable("employeeId")String employeeId)throws OutPunchingException, ResourceNotFoundException
    {
        AttendanceDto result=attendanceServices.outPunch(employeeId);

        Map<String,String> map=new HashMap<>();
        map.put("Employee Id",result.getEmployeeId());
        map.put("Status","Done");
        map.put("Date",result.getDate().toString());
        map.put("In Time",result.getInTime().toString());
        map.put("Out Time",result.getOutTime().toString());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<AttendanceDto>>findAttendanceListByEmployeeId(@PathVariable("employeeId") String employeeId)throws EmptyException,ResourceNotFoundException
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(attendanceServices.findByEmployeeId(employeeId));
    }

    @GetMapping("/{employeeId}/{date}")
    public ResponseEntity<AttendanceDto>findByEmployeeIdAndDate(@PathVariable String employeeId,@PathVariable LocalDate date)throws EmptyException,ResourceNotFoundException
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(attendanceServices.findByEmployeeIdAndDate(employeeId,date));
    }

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<List<AttendanceDto>>findAttendanceListByFromeDateToDate(@PathVariable("from") LocalDate from,@PathVariable("to") LocalDate to)throws EmptyException
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(attendanceServices.findByFromeDateToDate(from,to));
    }

    @GetMapping("/id/{employeeId}/from/{from}/to/{to}")
    public ResponseEntity<List<AttendanceDto>>findAttendanceListByEmployeeIdAndFromeDateToDate(@PathVariable("employeeId") String employeeId,@PathVariable("from") LocalDate from,@PathVariable("to") LocalDate to)throws ResourceNotFoundException,EmptyException
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(attendanceServices.findByEmployeeIdAndFromeDateToDate(employeeId,from,to));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<AttendanceDto>>findByDate(@PathVariable("date") LocalDate date)throws EmptyException
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(attendanceServices.findByDate(date));
    }

    @GetMapping("/date/{date}/{keyword}")
    public ResponseEntity<List<AttendanceDto>>findByPresentAndAbsent(@PathVariable("date") LocalDate date,@PathVariable("keyword") String keyword)throws EmptyException
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(attendanceServices.findByPresentAndAbsent(date,keyword));
    }


}

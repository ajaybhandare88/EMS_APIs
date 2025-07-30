package com.employee.attendance.services;

import com.employee.attendance.Dto.AttendanceDto;
import com.employee.attendance.exception.EmptyException;
import com.employee.attendance.exception.InPunchingException;
import com.employee.attendance.exception.OutPunchingException;
import com.employee.attendance.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceServices {

    AttendanceDto inPunch(String employeeId)throws InPunchingException,ResourceNotFoundException;

    AttendanceDto outPunch(String employeeId)throws OutPunchingException,ResourceNotFoundException;

    List<AttendanceDto> findByEmployeeId(String employeeId)throws EmptyException,ResourceNotFoundException;

    AttendanceDto findByEmployeeIdAndDate(String employeeId, LocalDate date)throws EmptyException,ResourceNotFoundException;

    List<AttendanceDto> findByFromeDateToDate(LocalDate fromeDate,LocalDate toDate)throws EmptyException;

    List<AttendanceDto> findByEmployeeIdAndFromeDateToDate(String employeeId,LocalDate fromeDate,LocalDate toDate)throws ResourceNotFoundException, EmptyException;

    List<AttendanceDto> findByDate(LocalDate date)throws EmptyException;

    List<AttendanceDto> findByPresentAndAbsent(LocalDate date,String attendance)throws EmptyException;




}

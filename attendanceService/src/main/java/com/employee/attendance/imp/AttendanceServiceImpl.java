package com.employee.attendance.imp;

import com.employee.attendance.Dto.AttendanceDto;
import com.employee.attendance.entities.Attendance;
import com.employee.attendance.exception.EmptyException;
import com.employee.attendance.exception.InPunchingException;
import com.employee.attendance.exception.OutPunchingException;
import com.employee.attendance.exception.ResourceNotFoundException;
import com.employee.attendance.external.services.EmployeeServices;
import com.employee.attendance.repositories.AttendanceRepo;
import com.employee.attendance.services.AttendanceServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceServices {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private EmployeeServices employeeService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Override
    public AttendanceDto inPunch(String employeeId)throws ResourceNotFoundException,InPunchingException {

        try {
          employeeService.getEmployeeById(employeeId);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        LocalDate today = LocalDate.now();
        Optional<Attendance> existing = attendanceRepo.findByEmployeeIdAndDate(employeeId, today);

        if (existing.isEmpty())
        {
            Attendance attendance=attendanceRepo.save(new Attendance(today,LocalTime.now(),null,"Present",employeeId));
            return entitiToDto(attendance);
        }

        else {
            throw new InPunchingException(employeeId,today,"In",existing.get().getInTime(),null);
        }
    }

    @Override
    public AttendanceDto outPunch(String employeeId)throws ResourceNotFoundException,OutPunchingException{

        try {
            employeeService.getEmployeeById(employeeId);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        LocalDate today = LocalDate.now();
        Optional<Attendance> existing = attendanceRepo.findByEmployeeIdAndDate(employeeId, today);

        if (existing.isEmpty())
        {
            throw new OutPunchingException("You did not marke In punch ",LocalDate.now(),employeeId);
        }

        Attendance attendance=existing.get();
        attendance.setOutTime(LocalTime.now());
        attendance.setFullDay(true);
        attendance.setHalfDay(true);
        Attendance attendance1=attendanceRepo.save(attendance);
        return entitiToDto(attendance1);


    }

    @Override
    public List<AttendanceDto> findByEmployeeId(String employeeId) throws ResourceNotFoundException,EmptyException{

        try {
            employeeService.getEmployeeById(employeeId);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        List<Attendance> listOfAttendance=attendanceRepo.findByEmployeeId(employeeId);

        if (listOfAttendance.isEmpty())
        {
            throw new EmptyException("No Attendance available on employee id = "+employeeId);
        }
        List<AttendanceDto> attendanceDtos=new ArrayList<>();
        listOfAttendance.forEach((e)->{
            attendanceDtos.add(entitiToDto(e));
        });

        return attendanceDtos;
    }

    @Override
    public AttendanceDto findByEmployeeIdAndDate(String employeeId, LocalDate date) throws ResourceNotFoundException,EmptyException{

        try {
            employeeService.getEmployeeById(employeeId);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        Optional<Attendance> result = attendanceRepo.findByEmployeeIdAndDate(employeeId, date);

        if (result.isEmpty())
        {
            throw new EmptyException("Attendance",date);
        }
        AttendanceDto attendanceDto=entitiToDto(result.get());
        return attendanceDto;
    }

    @Override
    public List<AttendanceDto> findByFromeDateToDate(LocalDate fromeDate, LocalDate toDate) throws EmptyException{

        List<Attendance> result=attendanceRepo.findByDetailsFromToEnd(fromeDate,toDate);
        if (result.isEmpty())
        {
            throw new EmptyException("Attendance",fromeDate,toDate);
        }
        List<AttendanceDto> attendanceDtos=new ArrayList<>();
        result.forEach((e)->{
            attendanceDtos.add(entitiToDto(e));
        });
        return attendanceDtos;
    }

    @Override
    public List<AttendanceDto> findByEmployeeIdAndFromeDateToDate(String employeeId, LocalDate fromeDate, LocalDate toDate) throws ResourceNotFoundException, EmptyException {

        try {
            employeeService.getEmployeeById(employeeId);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException("Employee","id",employeeId);
        }

        List<Attendance> result=attendanceRepo.findByDetailsEmployeeIdAndFromToEnd(employeeId,fromeDate,toDate);
        if (result.isEmpty())
        {
            throw new EmptyException("Attendance",fromeDate,toDate);
        }
        List<AttendanceDto> attendanceDtos=new ArrayList<>();
        result.forEach((e)->{
            attendanceDtos.add(entitiToDto(e));
        });
        return attendanceDtos;

    }


    @Override
    public List<AttendanceDto> findByDate(LocalDate date)throws EmptyException {

        List<Attendance> result=attendanceRepo.findByDate(date);

        if (result.isEmpty())
        {
            throw new EmptyException("Attendance",date);
        }
        List<AttendanceDto> attendanceDtos=new ArrayList<>();
        result.forEach((e)->{
            attendanceDtos.add(entitiToDto(e));
        });
        return attendanceDtos;

    }

    @Override
    public List<AttendanceDto> findByPresentAndAbsent(LocalDate date,String attendance) throws EmptyException{

        List<Attendance> result=attendanceRepo.findByDateAndStatus(date,attendance);

        if (result.isEmpty())
        {
            throw new EmptyException(attendance+" employees",date);
        }

        List<AttendanceDto> attendanceDtos=new ArrayList<>();
        result.forEach((e)->{
            attendanceDtos.add(entitiToDto(e));
        });
        return attendanceDtos;
    }


    public AttendanceDto entitiToDto(Attendance attendance)
    {
        AttendanceDto attendanceDto=modelMapper.map(attendance,AttendanceDto.class);
        return attendanceDto;
    }

    public Attendance dtoToEntiti(AttendanceDto attendanceDto)
    {
        Attendance attendance=modelMapper.map(attendanceDto,Attendance.class);
        return attendance;
    }
}

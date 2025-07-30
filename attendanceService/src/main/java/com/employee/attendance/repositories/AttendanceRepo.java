package com.employee.attendance.repositories;

import com.employee.attendance.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepo extends JpaRepository<Attendance,Integer> {

    Optional<Attendance> findByEmployeeIdAndDate(String employeeId, LocalDate date);
    List<Attendance> findByEmployeeId(String employeeId);
    List<Attendance> findByDate(LocalDate date);
    List<Attendance> findByDateAndStatus(LocalDate date, String status);



    @Query("SELECT a FROM Attendance AS a WHERE a.date BETWEEN :startDate AND :endDate")
    public List<Attendance> findByDetailsFromToEnd(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endtDate);

    @Query("SELECT a FROM Attendance AS a WHERE a.employeeId = :employeeId AND a.date BETWEEN :startDate AND :endDate")
    public List<Attendance> findByDetailsEmployeeIdAndFromToEnd(@Param("employeeId")String employeeId,@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endtDate);

}

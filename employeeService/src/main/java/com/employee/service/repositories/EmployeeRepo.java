package com.employee.service.repositories;

import com.employee.service.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,String> {

    public List<Employee> findByEmployeeName(String name);

}

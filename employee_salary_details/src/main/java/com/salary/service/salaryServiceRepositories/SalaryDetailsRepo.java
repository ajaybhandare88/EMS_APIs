package com.salary.service.salaryServiceRepositories;

import com.salary.service.entiti.EmployeeSalaryDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryDetailsRepo extends JpaRepository<EmployeeSalaryDetails,Integer> {

    public EmployeeSalaryDetails findByEmployeeId(String employeeId);

}

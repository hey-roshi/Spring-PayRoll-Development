package com.example.SpringEmployeePayRollDev.repository;

import com.example.SpringEmployeePayRollDev.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // Marks this as a repository for data access
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

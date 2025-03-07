package com.example.SpringEmployeePayRollDev.controller;
import com.example.SpringEmployeePayRollDev.exception.EmployeeNotFoundException;
import com.example.SpringEmployeePayRollDev.model.Employee;
import com.example.SpringEmployeePayRollDev.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // 1️⃣ Get All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    // 2️⃣ Get Employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with ID: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
        return ResponseEntity.ok(employee);
    }

    // 3️⃣ Add a New Employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        log.info("Adding new employee: {}", employee);
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    // 4️⃣ Update an Employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employeeDetails) {
        log.info("Updating employee with ID: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

        employee.setName(employeeDetails.getName());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setSalary(employeeDetails.getSalary());
        return ResponseEntity.ok(employeeRepository.save(employee));
    }


    // 5️⃣ Delete an Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with ID: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

        employeeRepository.delete(employee);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}

//@RestController  // Marks this as a REST API Controller
//@RequestMapping("/employees")  // Base URL for API endpoints
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    // Get all employees
//    @GetMapping
//    public List<Employee> getAllEmployees() {
//        return employeeService.getAllEmployees();
//    }
//
//    // Get employee by ID
//    @GetMapping("/{id}")
//    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
//        return employeeService.getEmployeeById(id);
//    }
//
//    // Add a new employee
//    @PostMapping
//    public Employee addEmployee(@RequestBody Employee employee) {
//        return employeeService.saveEmployee(employee);
//    }
//
//    // Update an employee
//    @PutMapping("/{id}")
//    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
//        return employeeService.updateEmployee(id, updatedEmployee);
//    }
//
//    // Delete an employee
//    @DeleteMapping("/{id}")
//    public void deleteEmployee(@PathVariable Long id) {
//        employeeService.deleteEmployee(id);
//    }
//}
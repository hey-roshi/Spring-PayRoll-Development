package com.example.SpringEmployeePayRollDev.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
@Entity  // Marks this class as a database entity
@Table(name = "employees")  // Specifies the table name
@Data  // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor  // Generates constructor with all fields
@NoArgsConstructor  // Generates a no-argument constructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increments ID
    private Long id;
    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Name should contain only alphabets and spaces")
    private String name;
    private String department;
    private double salary;
}
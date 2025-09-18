package com.example.springboot.employees.repository;

import com.example.springboot.employees.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

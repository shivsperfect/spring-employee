package com.example.springboot.employees.service;

import com.example.springboot.employees.entity.Employee;
import com.example.springboot.employees.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(long id);

    Employee save(EmployeeRequest employeeRequest);

    Employee update(long id, EmployeeRequest employeeRequest);

    Employee convertToEmployee(long id, EmployeeRequest employeeRequest);

    void deleteById(long id);
}

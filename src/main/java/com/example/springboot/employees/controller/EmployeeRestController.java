package com.example.springboot.employees.controller;

import com.example.springboot.employees.entity.Employee;
import com.example.springboot.employees.request.EmployeeRequest;
import com.example.springboot.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Rest API Endpoints", description = "Operations related to employees.")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }

    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @Operation(summary = "Get an employee by ID", description = "Retrieve an employee by ID.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable @Min(value = 1) long id) {

        return employeeService.findById(id);
    }

    @Operation(summary = "Add an employee", description = "Add a new employee.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee addEmployee(@Valid @RequestBody  EmployeeRequest employeeRequest) {
        Employee employee = employeeService.save(employeeRequest);
        return employee;
    }

    @Operation(summary = "Update an employee", description = "Update an existing employee.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable @Min(value = 1) long id, @Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.update(id, employeeRequest);
        return employee;
    }

    @Operation(summary = "Delete an employee", description = "Remove an employee from the database.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable @Min(value = 1) long id) {
        employeeService.deleteById(id);
    }
}

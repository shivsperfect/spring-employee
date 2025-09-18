package com.example.springboot.employees.service;

import com.example.springboot.employees.entity.Employee;
import com.example.springboot.employees.repository.EmployeeRepository;
import com.example.springboot.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        Optional<Employee> result = employeeRepository.findById(id);
        return result.orElseThrow(() -> new RuntimeException("Employee not found for id :: " + id));
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest employeeRequest) {
        Employee employee  = convertToEmployee(0, employeeRequest);
        return employeeRepository.save(employee) ;
    }

    @Transactional
    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee employee  = convertToEmployee(id, employeeRequest);
        return employeeRepository.save(employee) ;
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(id,  employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getEmail());
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }
}

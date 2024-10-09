package org.example.backend.service;


import org.example.backend.model.Employee;
import org.example.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;



    public void addEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployee(id);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }



}

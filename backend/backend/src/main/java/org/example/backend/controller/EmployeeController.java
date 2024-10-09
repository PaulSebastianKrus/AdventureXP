package org.example.backend.controller;

import org.example.backend.model.Employee;
import org.example.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;



    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return ResponseEntity.ok("Employee added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }


    @PostMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee newEmployee) {
        Employee oldEmployee = employeeService.getEmployeeById(id);

        if (oldEmployee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }


        // Only update fields that are not null
        if (newEmployee.getName() != null) {
            oldEmployee.setName(newEmployee.getName());
        }
        if (newEmployee.getRole() != null) {
            oldEmployee.setRole(newEmployee.getRole());
        }

        if (newEmployee.getActivities() != null) {
            oldEmployee.setActivities(newEmployee.getActivities());
        }

        employeeService.updateEmployee(oldEmployee);
        return ResponseEntity.ok("Employee updated");

    }






}

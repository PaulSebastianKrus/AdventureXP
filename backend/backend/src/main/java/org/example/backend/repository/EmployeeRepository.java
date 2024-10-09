package org.example.backend.repository;

import org.example.backend.model.Activity;
import org.example.backend.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void addEmployee(Employee employee) {
        String insertQuery = "INSERT INTO employees (name, role, activities) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertQuery, employee.getName(), employee.getRole(), employee.getActivities());
    }

    public void deleteEmployee(Long id) {
        String query = "DELETE FROM employees WHERE id = ?";
        jdbcTemplate.update(query, id);
    }


    public List<Employee> getAllEmployees() {
        String query = "SELECT * FROM employees";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setId(rs.getLong("id"));
            employee.setName(rs.getString("name"));
            employee.setRole(rs.getString("role"));
            employee.setActivities(rs.getString("activities"));
            return employee;
        });
    }

    public Employee getEmployeeById(Long id) {
        String query = "SELECT * FROM employees WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setId(rs.getLong("id"));
            employee.setName(rs.getString("name"));
            employee.setRole(rs.getString("role"));
            employee.setActivities(rs.getString("activities"));
            return employee;
        });
    }

    public Employee updateEmployee(Employee employee) {
        StringBuilder query = new StringBuilder("UPDATE employees SET ");
        List<Object> parameters = new ArrayList<>();

        if (employee.getName() != null) {
            query.append("name = ?, ");
            parameters.add(employee.getName());
        }
        if (employee.getRole() != null) {
            query.append("role = ?, ");
            parameters.add(employee.getRole());
        }
        if (employee.getActivities() != null) {
            query.append("activities = ?, ");
            parameters.add(employee.getActivities());
        }


        if (!parameters.isEmpty()) {
            query.setLength(query.length() - 2); // Remove last comma and space
            query.append(" WHERE id = ?");
            parameters.add(employee.getId());

            int rowsUpdated = jdbcTemplate.update(query.toString(), parameters.toArray());
            if (rowsUpdated > 0) {
                return employee;
            }
        }
        return null;
    }

}

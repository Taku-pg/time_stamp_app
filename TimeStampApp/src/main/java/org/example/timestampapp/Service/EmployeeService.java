package org.example.timestampapp.Service;

import org.example.timestampapp.Model.Entity.Employee;
import org.example.timestampapp.Model.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(long id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        return employee;
    }
}

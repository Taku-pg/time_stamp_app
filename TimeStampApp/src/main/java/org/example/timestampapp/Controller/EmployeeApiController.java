package org.example.timestampapp.Controller;

import org.example.timestampapp.Model.Entity.Employee;
import org.example.timestampapp.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeApiController {
    private final EmployeeService employeeService;
    public EmployeeApiController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/api/employee/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee;
    }
}

package org.example.timestampapp.Controller.ApiController;

import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id).orElse(null);
        if (employee == null) {
            System.out.println("No employee found with id " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            System.out.println(employee);
        }
        return ResponseEntity.ok(employee);
    }
}


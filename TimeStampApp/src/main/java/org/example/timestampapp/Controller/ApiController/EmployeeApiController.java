package org.example.timestampapp.Controller.ApiController;

import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Model.DTO.EmployeeStatisticsDTO;
import org.example.timestampapp.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeApiController {
    private final EmployeeService employeeService;
    public EmployeeApiController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
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

    @GetMapping("/{id}/statistics/{year}/{month}")
    public ResponseEntity<EmployeeStatisticsDTO> getEmployeeWorkingStatistics(@PathVariable long id,
                                                                              @PathVariable int year,
                                                                              @PathVariable int month) {
        EmployeeStatisticsDTO statistics=employeeService.getEmployeeWorkingStatistics(id,year,month);
        System.out.println("from api "+statistics);
        if(statistics==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(statistics);
    }
}


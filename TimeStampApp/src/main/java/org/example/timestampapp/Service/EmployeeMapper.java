package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.*;
import org.example.timestampapp.Model.Entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public EmployeeDTO map(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setRole(employee.getUser().getRole());
        employeeDTO.setPassword(employee.getUser().getPassword());
        employeeDTO.setDepartment(employee.getDepartment().getName());
        employeeDTO.setStatus(employee.getStatus().getType());

        return employeeDTO;
    }
}


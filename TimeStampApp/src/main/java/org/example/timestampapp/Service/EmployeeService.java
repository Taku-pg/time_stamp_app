package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Model.Entity.Employee;
import org.example.timestampapp.Model.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeDTO getEmployeeById(long id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        EmployeeDTO employeeDTO = employeeMapper.map(employee);
        return employeeDTO;
    }
}


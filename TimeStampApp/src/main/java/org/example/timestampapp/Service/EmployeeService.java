package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Model.Entity.Employee;
import org.example.timestampapp.Model.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(long id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if (employee == null) {
            return Optional.empty();
        }
        return Optional.of(employeeMapper.map(employee));
    }

    public List<EmployeeDTO> getAllEmployees() {
        Iterable<Employee> employees=employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS=new ArrayList<>();

        for (Employee employee : employees) {
            employeeDTOS.add(employeeMapper.map(employee));
        }
        return employeeDTOS;
    }

    public void updateEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(employeeMapper.map(employeeDTO));
    }
}


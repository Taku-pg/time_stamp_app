package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Model.DTO.EmployeeWorkingStatisticsDTO;
import org.example.timestampapp.Model.Entity.Department;
import org.example.timestampapp.Model.Entity.Employee;
import org.example.timestampapp.Model.Repository.DepartmentRepository;
import org.example.timestampapp.Model.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final WorkingHourService workingHourService;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeMapper employeeMapper,
                           WorkingHourService workingHourService,
                           DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.workingHourService = workingHourService;
        this.departmentRepository = departmentRepository;
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

    public EmployeeWorkingStatisticsDTO getEmployeeWorkingStatistics(long employeeId, int year, int month) {
        EmployeeWorkingStatisticsDTO record = workingHourService.getWorkingHourStatistics(employeeId,year,month);
        return record;
    }

    @Transactional
    public void updateEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(employeeMapper.map(employeeDTO));
    }

    @Transactional
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}


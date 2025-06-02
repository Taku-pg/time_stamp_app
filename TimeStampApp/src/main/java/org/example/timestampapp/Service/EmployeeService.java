package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Model.DTO.EmployeeStatisticsDTO;
import org.example.timestampapp.Model.Entity.Department;
import org.example.timestampapp.Model.Entity.Employee;
import org.example.timestampapp.Model.Entity.Status;
import org.example.timestampapp.Model.Entity.User;
import org.example.timestampapp.Model.Repository.DepartmentRepository;
import org.example.timestampapp.Model.Repository.EmployeeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final StatisticsService statisticsService;
    private final DepartmentRepository departmentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeMapper employeeMapper,
                           StatisticsService statisticsService,
                           DepartmentRepository departmentRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.statisticsService = statisticsService;
        this.departmentRepository = departmentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<EmployeeDTO> getEmployeeById(long id) {
        Employee employee = employeeRepository.getEmployeeById(id).orElse(null);
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

    public EmployeeStatisticsDTO getEmployeeWorkingStatistics(long employeeId, int year, int month) {
        return statisticsService.getWorkingHourStatistics(employeeId,year,month);
    }

    public void createEmployee(EmployeeDTO employeeDTO, Department department, Status status) {
        String rawPassword = "Password";
        String hashedPassword = bCryptPasswordEncoder.encode(rawPassword);
        User user =new User(employeeDTO.getEmail(),hashedPassword);

        Employee employee = new Employee(employeeDTO.getFirstName(),
                                         employeeDTO.getLastName(),
                                         employeeDTO.getEmail(),
                                         employeeDTO.getSalary(),
                                         department,
                                         user,
                                         status);
        employeeRepository.save(employee);
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


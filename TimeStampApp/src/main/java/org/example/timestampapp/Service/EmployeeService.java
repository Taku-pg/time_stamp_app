package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Model.DTO.EmployeeHistoryDTO;
import org.example.timestampapp.Model.DTO.EmployeeStatisticsDTO;
import org.example.timestampapp.Model.DTO.EmployeeStatusDTO;
import org.example.timestampapp.Model.Entity.*;
import org.example.timestampapp.Model.Repository.DepartmentRepository;
import org.example.timestampapp.Model.Repository.EmployeeRepository;
import org.example.timestampapp.Model.Repository.UserRepository;
import org.example.timestampapp.Model.Repository.WorkingHourRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final EmployeeMapper employeeMapper;
    private final StatisticsService statisticsService;
    private final StatusService statusService;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final WorkingHourService workingHourService;
    private final WorkingHourRepository workingHourRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           UserRepository userRepository,
                           EmployeeMapper employeeMapper,
                           StatisticsService statisticsService,
                           StatusService statusService,
                           DepartmentRepository departmentRepository,
                           PasswordEncoder bCryptPasswordEncoder, WorkingHourService workingHourService, WorkingHourRepository workingHourRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.employeeMapper = employeeMapper;
        this.statisticsService = statisticsService;
        this.statusService = statusService;
        this.departmentRepository = departmentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.workingHourService = workingHourService;
        this.workingHourRepository = workingHourRepository;
    }

    public EmployeeDTO getEmployeeById(long id) {
        Employee employee = employeeRepository.getEmployeeById(id).orElse(null);
        if (employee == null) {
            throw new NoSuchElementException("Employee with id " + id + " not found");
        }
        return employeeMapper.map(employee);
    }

    public EmployeeStatusDTO getEmployeeStatusByEmail(String email) {
        Employee employee=employeeRepository.findEmployeeByEmail(email).orElse(null);
        if (employee == null) {
            throw new NoSuchElementException("Employee with email " + email + " not found");
        }
        return employeeMapper.mapEmployeeStatusDTO(employee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        Iterable<Employee> employees=employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS=new ArrayList<>();

        for (Employee employee : employees) {
            employeeDTOS.add(employeeMapper.map(employee));
        }
        return employeeDTOS;
    }

    public EmployeeStatisticsDTO getEmployeeWorkingStatistics(Long employeeId, int year, int month) {
        return statisticsService.getWorkingHourStatistics(employeeId,year,month);
    }

    public List<EmployeeHistoryDTO> getEmployeeHistory(Long employeeId, int year, int month) {
        return workingHourService.getEmployeeHistory(employeeId,year,month);
    }

    @Transactional
    public void registerEmployee(EmployeeDTO employeeDTO, Department department, Status status) {
        String rawPassword = "Password";
        String hashedPassword = bCryptPasswordEncoder.encode(rawPassword);
        User user =new User(employeeDTO.getEmail(),hashedPassword);
        user=userRepository.save(user);

        Employee employee = new Employee(employeeDTO.getFirstName(),
                                         employeeDTO.getLastName(),
                                         employeeDTO.getEmail(),
                                         employeeDTO.getSalary(),
                                         department,
                                         user,
                                         status);
        user.setEmployee(employee);
        employeeRepository.save(employee);
    }

    @Transactional
    public void updateEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(employeeMapper.map(employeeDTO));
    }

    @Transactional
    public void deleteEmployee(Long employeeId) {
        System.out.println("call delete");
        employeeRepository.deleteById(employeeId);
        System.out.println(employeeRepository.existsById(employeeId));
    }

    @Transactional
    public void workTimeStamp(Long employeeId) {
        Employee employee = employeeRepository.getEmployeeById(employeeId).orElse(null);
        if (employee == null) {
            throw new NoSuchElementException("Employee with id " + employeeId + " not found");
        }
        workingHourService.workTimeStamp(employee);
        timeStamp(employee,"Work");
    }

    @Transactional
    public void leaveTimeStamp(Long employeeId) {
        Employee employee = employeeRepository.getEmployeeById(employeeId).orElse(null);
        if (employee == null) {
            throw new NoSuchElementException("Employee with id " + employeeId + " not found");
        }
        workingHourService.leaveTimeStamp(employeeId);
        timeStamp(employee,"Leave");
    }

    @Transactional
    public void breakTimeStamp(Long employeeId) {
        Employee employee = employeeRepository.getEmployeeById(employeeId).orElse(null);
        if (employee == null) {
            throw new NoSuchElementException("Employee with id " + employeeId + " not found");
        }
        workingHourService.breakTimeStamp(employeeId);
        timeStamp(employee,"Break");
    }

    @Transactional
    public void backTimeStamp(Long employeeId) {
        Employee employee = employeeRepository.getEmployeeById(employeeId).orElse(null);
        if (employee == null) {
            throw new NoSuchElementException("Employee with id " + employeeId + " not found");
        }
        workingHourService.backTimeStamp(employeeId);
        timeStamp(employee,"Work");
    }


    private void timeStamp(Employee employee,String type){
        Status status=statusService.getStatus(type);
        employee.setStatus(status);
        employee.setLastUpdate(LocalDateTime.now());
        employeeRepository.save(employee);
    }
}


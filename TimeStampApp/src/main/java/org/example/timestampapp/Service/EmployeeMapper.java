package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.*;
import org.example.timestampapp.Model.Entity.Department;
import org.example.timestampapp.Model.Entity.Employee;
import org.example.timestampapp.Model.Repository.DepartmentRepository;
import org.example.timestampapp.Model.Repository.EmployeeRepository;
import org.example.timestampapp.Model.Repository.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final StatusRepository statusRepository;

    public EmployeeMapper(EmployeeRepository employeeRepository,
                          DepartmentRepository departmentRepository,
                          StatusRepository statusRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.statusRepository = statusRepository;
    }

    public EmployeeDTO map(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setSalary(employee.getSalary());
        /*employeeDTO.setRole(employee.getUser().getRole());
        employeeDTO.setPassword(employee.getUser().getPassword());*/
        employeeDTO.setDepartment(employee.getDepartment().getName());
        //employeeDTO.setStatus(employee.getStatus().getType());

        return employeeDTO;
    }

    public Employee map(EmployeeDTO employeeDTO) {
        System.out.println(employeeDTO.getEmployeeId());
        Employee employee= employeeRepository.getEmployeeById(employeeDTO.getEmployeeId()).orElse(null);

        if(employee!=null) {
            if (employeeDTO.getFirstName() != null)
                employee.setFirstName(employeeDTO.getFirstName());

            if (employeeDTO.getLastName() != null)
                employee.setLastName(employeeDTO.getLastName());

            if (employeeDTO.getEmail() != null)
                employee.setEmail(employeeDTO.getEmail());

            if (employeeDTO.getSalary() != null)
                employee.setSalary(employeeDTO.getSalary());

            if (employeeDTO.getDepartment() != null) {
                departmentRepository.findDepartmentByName(employeeDTO.getDepartment()).ifPresent(employee::setDepartment);
            }
        }

        return employee;
    }

    public EmployeeStatusDTO mapEmployeeStatusDTO(Employee employee) {
        EmployeeStatusDTO employeeStatusDTO = new EmployeeStatusDTO();
        employeeStatusDTO.setEmployeeId(employee.getId());
        employeeStatusDTO.setFirstName(employee.getFirstName());
        employeeStatusDTO.setCurrentStatus(employee.getStatus().getType());
        employeeStatusDTO.setLastUpdate(employee.getLastUpdate());
        return employeeStatusDTO;
    }
}


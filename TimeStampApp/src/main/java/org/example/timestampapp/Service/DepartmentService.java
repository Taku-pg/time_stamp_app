package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.DepartmentStatisticsDTO;
import org.example.timestampapp.Model.Entity.Department;
import org.example.timestampapp.Model.Repository.DepartmentRepository;
import org.example.timestampapp.Model.Repository.WorkingHourRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final WorkingHourService workingHourService;

    public DepartmentService(DepartmentRepository departmentRepository,
                             WorkingHourService workingHourService) {
        this.departmentRepository = departmentRepository;
        this.workingHourService = workingHourService;
    }

    public List<String> getAllDepartmentName(){
        Iterable<Department> departments = departmentRepository.findAll();
        List<String> departmentNames = new ArrayList<>();
        for (Department department : departments) {
            departmentNames.add(department.getName());
        }
        return departmentNames;
    }

    public String getDeptName(){
        Iterable<Department> departments = departmentRepository.findAll();
        return departments.iterator().next().getName();
    }

    public DepartmentStatisticsDTO getDeptStatistics(String dName, int year, int month) {
        return workingHourService.getDeptStatistics(dName,year,month);
    }
}

package org.example.timestampapp.Service;

import org.example.timestampapp.Model.DTO.DepartmentStatisticsDTO;
import org.example.timestampapp.Model.Entity.Department;
import org.example.timestampapp.Model.Repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final StatisticsService statisticsService;

    public DepartmentService(DepartmentRepository departmentRepository,
                             StatisticsService statisticsService) {
        this.departmentRepository = departmentRepository;
        this.statisticsService = statisticsService;
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
        return statisticsService.getDeptStatistics(dName,year,month);
    }
}

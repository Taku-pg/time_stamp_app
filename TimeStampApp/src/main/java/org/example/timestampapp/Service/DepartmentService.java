package org.example.timestampapp.Service;

import org.example.timestampapp.Model.Entity.Department;
import org.example.timestampapp.Model.Repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<String> getAllDepartmentName(){
        Iterable<Department> departments = departmentRepository.findAll();
        List<String> departmentNames = new ArrayList<>();
        for (Department department : departments) {
            departmentNames.add(department.getName());
        }
        return departmentNames;
    }
}

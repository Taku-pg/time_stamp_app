package org.example.timestampapp.Model.Repository;

import org.example.timestampapp.Model.Entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department findDepartmentByName(String name);
}

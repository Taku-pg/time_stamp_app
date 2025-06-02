package org.example.timestampapp.Model.Repository;

import org.example.timestampapp.Model.Entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Optional<Department> findDepartmentByName(String name);
}

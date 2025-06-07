package org.example.timestampapp.Repository;

import org.example.timestampapp.Model.Entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    Optional<Employee> getEmployeeById(Long id);

    Optional<Employee> findEmployeeByEmail(String email);
}

package org.example.timestampapp.Model.Repository;

import org.example.timestampapp.Model.Entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    Employee getEmployeeById(Long id);
}

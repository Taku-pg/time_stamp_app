package org.example.timestampapp.Model.DTO;

import jakarta.validation.constraints.*;
import org.example.timestampapp.Validation.ValidEmail;

@ValidEmail
public class EmployeeDTO {
    private Long employeeId;
    @NotBlank(message = "{fName.null}")
    @Size(min = 1, max = 20, message = "{fName.size}")
    private String firstName;
    @NotBlank(message = "{lName.null}")
    @Size(min = 1, max = 20, message="{lName.size}")
    private String lastName;
    @NotBlank(message = "{email.null}")
    @Email
    private String email;
    @NotNull(message = "{salary.null}")
    @Min(value = 10, message = "{salary.min}")
    private Integer salary;
    @NotNull(message = "{dept.null}")
    private String department;

    @Override
    public String toString() {
        return employeeId+" "+firstName + " " + lastName + " " + email + " " + salary+" "+department;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

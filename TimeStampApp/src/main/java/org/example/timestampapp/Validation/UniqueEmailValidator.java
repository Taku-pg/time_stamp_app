package org.example.timestampapp.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.timestampapp.Model.DTO.EmployeeDTO;
import org.example.timestampapp.Model.Entity.Employee;
import org.example.timestampapp.Model.Repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class UniqueEmailValidator implements ConstraintValidator<ValidEmail, EmployeeDTO> {

    private final EmployeeRepository employeeRepository;

    public UniqueEmailValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EmployeeDTO employeeDTO, ConstraintValidatorContext context) {
        boolean valid;
        Optional<Employee> hasExist=employeeRepository.findEmployeeByEmail(employeeDTO.getEmail());

        if(employeeDTO.getEmployeeId()!=null){
            //when update employee info(with id)
            System.out.println("email error catch in update");
            valid = hasExist.isEmpty() || hasExist.get().getId().equals(employeeDTO.getEmployeeId());
        }else{
            //when register new employee
            System.out.println("email error catch in register");
            valid=hasExist.isEmpty();
        }

        if(!valid){
            context.buildConstraintViolationWithTemplate("{email.notUnique}").addPropertyNode("email").addConstraintViolation();
            context.disableDefaultConstraintViolation();
        }

        return valid;
    }
}

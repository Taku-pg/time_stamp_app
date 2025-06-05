package org.example.timestampapp.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        boolean valid = true;

        if(password.length() < 8) {
            valid = false;
            context.buildConstraintViolationWithTemplate("{password.length}").addConstraintViolation();
        }

        if(!password.matches(".*[!-/:-@-\\[\\]`{-~].*")) {
            valid = false;
            context.buildConstraintViolationWithTemplate("{password.special}").addConstraintViolation();
        }

        if(!password.matches(".*[a-z].*")){
            valid = false;
            context.buildConstraintViolationWithTemplate("{password.lowercase}").addConstraintViolation();
        }

        if(!password.matches(".*[A-Z].*")){
            valid = false;
            context.buildConstraintViolationWithTemplate("{password.uppercase}").addConstraintViolation();
        }

        if(!valid)
            context.disableDefaultConstraintViolation();

        return valid;
    }
}

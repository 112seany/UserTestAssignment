package com.example.userscrud.rest.validation;

import com.example.userscrud.exception.UserValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.Period;

public class DateValidator implements ConstraintValidator<DateConstraint, LocalDate> {

    @Value("${user.age.validation}")
    private int userMinAge;

    @Override
    public void initialize(DateConstraint birthDate) {
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if (birthDate.isAfter(LocalDate.now())) {
            throw new UserValidationException("Value must be earlier than current");
        } if (Period.between(birthDate, LocalDate.now()).getYears() < userMinAge) {
            throw new UserValidationException(String.format("User must be %s years old", userMinAge));
        }
        return true;
    }
}

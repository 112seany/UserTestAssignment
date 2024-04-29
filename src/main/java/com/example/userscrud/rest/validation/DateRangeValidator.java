package com.example.userscrud.rest.validation;

import com.example.userscrud.exception.UserValidationException;
import com.example.userscrud.rest.request.FindUsersFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class DateRangeValidator implements ConstraintValidator<DateRangeConstraint, FindUsersFilter> {

    @Override
    public boolean isValid(FindUsersFilter value, ConstraintValidatorContext context) {
        if (Objects.isNull(value.getFromDate()) || Objects.isNull(value.getToDate())) {
            throw new UserValidationException("Birth date cannot be null");
        }

        if (value.getFromDate().isBefore(value.getToDate())) {
            return true;
        }
        throw new UserValidationException("Date 'from' should be less than date 'to'");
    }
}

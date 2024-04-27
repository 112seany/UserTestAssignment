package com.example.userscrud.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRangeConstraint {

    String message() default "Date range constraint validation failed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

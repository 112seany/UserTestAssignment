package com.example.userscrud.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateConstraint {

    String message() default "Date constraint validation failed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

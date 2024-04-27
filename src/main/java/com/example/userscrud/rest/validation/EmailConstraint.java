package com.example.userscrud.rest.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConstraint {

    String message() default "Email constraint validation failed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

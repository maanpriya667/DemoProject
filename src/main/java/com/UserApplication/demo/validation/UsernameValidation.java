package com.UserApplication.demo.validation;

import com.UserApplication.demo.validation.validator.UsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
@Documented

public @interface UsernameValidation {
    String message() default "Invalid Username";        //error message

        Class<?>[] groups() default {};                   //represents group of constraints

        Class<? extends Payload>[] payload() default {};  //represents additional information about annotation
        }

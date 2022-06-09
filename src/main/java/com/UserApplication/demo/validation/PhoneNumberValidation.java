package com.UserApplication.demo.validation;


import com.UserApplication.demo.validation.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
@Documented

public @interface PhoneNumberValidation {
    String message() default "Invalid PhoneNumber";        //error message

    Class<?>[] groups() default {};                   //represents group of constraints

    Class<? extends Payload>[] payload() default {};  //represents additional information about annotation
}



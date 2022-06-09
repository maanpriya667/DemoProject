package com.UserApplication.demo.validation;

import com.UserApplication.demo.validation.validator.AddressValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddressValidator.class)
@Documented


public @interface AddressValidation {
    String message() default "Invalid Address.";        //error message

    Class<?>[] groups() default {};                   //represents group of constraints

    Class<? extends Payload>[] payload() default {};  //represents additional information about annotation
}



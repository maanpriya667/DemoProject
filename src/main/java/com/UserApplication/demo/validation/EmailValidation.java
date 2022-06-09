package com.UserApplication.demo.validation;



import com.UserApplication.demo.validation.validator.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented

public @interface EmailValidation {
    String message() default "Invalid Email.";        //error message

    Class<?>[] groups() default {};                   //represents group of constraints

    Class<? extends Payload>[] payload() default {};  //represents additional information about annotation
}

package com.UserApplication.demo.validation;

import com.UserApplication.demo.validation.validator.DobValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DobValidator.class)
@Documented

public @interface DobValidation {
    String message() default "Invalid Dob.";        //error message

    Class<?>[] groups() default {};                   //represents group of constraints

    Class<? extends Payload>[] payload() default {};  //represents additional information about annotation
}




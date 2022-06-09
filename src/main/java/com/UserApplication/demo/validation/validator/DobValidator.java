package com.UserApplication.demo.validation.validator;

import com.UserApplication.demo.validation.DobValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class DobValidator implements ConstraintValidator<DobValidation, String> {

    @Override
    public boolean isValid(String dob, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")
                .matcher(dob)
                .matches();
    }
}

package com.UserApplication.demo.validation.validator;

import com.UserApplication.demo.validation.PhoneNumberValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation,String> {
    @Override
    public boolean isValid(String ph_number, ConstraintValidatorContext context) {
        return Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
                .matcher(ph_number)
                .matches();
    }
}

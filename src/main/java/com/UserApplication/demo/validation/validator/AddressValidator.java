package com.UserApplication.demo.validation.validator;

import com.UserApplication.demo.validation.AddressValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class AddressValidator implements ConstraintValidator<AddressValidation, String> {

@Override
public boolean isValid(String address, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.compile("^[#.0-9a-zA-Z\\s,-]+$")
        .matcher(address)
        .matches();
        }
        }
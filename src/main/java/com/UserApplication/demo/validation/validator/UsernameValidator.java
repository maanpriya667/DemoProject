package com.UserApplication.demo.validation.validator;

import com.UserApplication.demo.validation.UsernameValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<UsernameValidation, String> {

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.compile("^[A-Za-z]\\w{5,29}$")
                .matcher(username)
                .matches();
    }
}


//    public boolean isValid(String ph_number, ConstraintValidatorContext context) {
//        return Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
//                .matcher(ph_number)
//                .matches();
//               }
//
//}

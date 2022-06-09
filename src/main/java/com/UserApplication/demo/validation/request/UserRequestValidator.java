package com.UserApplication.demo.validation.request;

import com.UserApplication.demo.model.request.DetailsRequest;
import com.UserApplication.demo.model.request.OTPRequest;
import com.UserApplication.demo.model.request.SignupRequest;
import com.UserApplication.demo.model.response.ResponseModel;
import com.UserApplication.demo.validation.groups.SignupInfoValidationGroup;
import com.UserApplication.demo.validation.groups.LoginInfoValidationGroup;
import com.UserApplication.demo.validation.groups.OtpInfoValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class UserRequestValidator {

    @Autowired
    private Validator validator;


    public ResponseModel<?> validateOtpRequest(OTPRequest otpRequest) {
        Set<ConstraintViolation<OTPRequest>> validate = validator.validate(otpRequest,
                OtpInfoValidationGroup.class); //Set<ConstraintViolation<T>> validate(T object, Class<?>... groups);
        return getOtpResponse(validate);
    }



    public ResponseModel<?> validateSignupRequest(SignupRequest signupRequest) {
        Set<ConstraintViolation<SignupRequest>> validate = validator.validate(signupRequest,
                SignupInfoValidationGroup.class);
        return getSignupResponse(validate);
    }



    public ResponseModel<?> validateLoginRequest(DetailsRequest detailsRequest) {
        Set<ConstraintViolation<DetailsRequest>> validate = validator.validate(detailsRequest,
                LoginInfoValidationGroup.class);
        return getLoginResponse(validate);
    }



    private ResponseModel<?> getOtpResponse(
            Set<ConstraintViolation<OTPRequest>> validate) {

        if (!validate.isEmpty()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (ConstraintViolation<OTPRequest> constraintViolation : validate) {
                if (constraintViolation.getPropertyPath().toString() != null
                        && !constraintViolation.getPropertyPath().toString().isEmpty())
                    errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                else {
                    String[] result = constraintViolation.getMessage().split(":");
                    return new ResponseModel<>(HttpStatus.BAD_REQUEST, result[1], null, null);
                }
            }
            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "Validation errors", null, null, errors);
        }
        return null;
    }


    private ResponseModel<?> getSignupResponse(
            Set<ConstraintViolation<SignupRequest>> validate) {

        if (!validate.isEmpty()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (ConstraintViolation<SignupRequest> constraintViolation : validate) {
                if (constraintViolation.getPropertyPath().toString() != null
                        && !constraintViolation.getPropertyPath().toString().isEmpty())
                    errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                else {
                    String[] result = constraintViolation.getMessage().split(":");
                    return new ResponseModel<>(HttpStatus.BAD_REQUEST, result[1], null, null);
                }
            }
            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "Validation errors", null, null, errors);
        }
        return null;
    }


    private ResponseModel<?> getLoginResponse(
            Set<ConstraintViolation<DetailsRequest>> validate) {

        if (!validate.isEmpty()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (ConstraintViolation<DetailsRequest> constraintViolation : validate) {
                if (constraintViolation.getPropertyPath().toString() != null
                        && !constraintViolation.getPropertyPath().toString().isEmpty())
                    errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                else {
                    String[] result = constraintViolation.getMessage().split(":");
                    return new ResponseModel<>(HttpStatus.BAD_REQUEST, result[1], null, null);
                }
            }
            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "Validation errors", null, null, errors);
        }
        return null;
    }
}


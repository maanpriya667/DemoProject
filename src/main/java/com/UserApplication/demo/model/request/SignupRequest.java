package com.UserApplication.demo.model.request;

import com.UserApplication.demo.validation.EmailValidation;
import com.UserApplication.demo.validation.groups.SignupInfoValidationGroup;

import javax.validation.constraints.NotNull;

public class SignupRequest {

    @NotNull
    private String otp;
    private String email;
    private String referral_code;
    private String device_name;
    private String device_model;


    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

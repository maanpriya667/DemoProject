package com.UserApplication.demo.model.request;


import com.UserApplication.demo.validation.EmailValidation;
import com.UserApplication.demo.validation.groups.OtpInfoValidationGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.transform.Source;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTPRequest implements Source {

    @EmailValidation(groups = OtpInfoValidationGroup.class)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getSystemId() {
        return null;
    }

    @Override
    public void setSystemId(String s) {

    }
}

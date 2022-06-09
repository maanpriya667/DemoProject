package com.UserApplication.demo.model.request;

import com.UserApplication.demo.validation.AddressValidation;
import com.UserApplication.demo.validation.DobValidation;
import com.UserApplication.demo.validation.PhoneNumberValidation;
import com.UserApplication.demo.validation.UsernameValidation;
import com.UserApplication.demo.validation.groups.LoginInfoValidationGroup;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DetailsRequest {

    @PhoneNumberValidation(groups = LoginInfoValidationGroup.class)
    private String ph_number;

    @DobValidation(groups = LoginInfoValidationGroup.class)
    private String dob;

   // @Column(length = 200)
    @NotNull
    @Size(min = 5,max = 200)
    @AddressValidation(groups = LoginInfoValidationGroup.class)
    private String address;

    @Column(name = "username")
    @UsernameValidation(groups = LoginInfoValidationGroup.class)
    private String username;

    public String getPh_number() {
        return ph_number;
    }

    public void setPh_number(String ph_number) {
        this.ph_number = ph_number;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.UserApplication.demo.model.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ProfileResponse {
    private String email;
    private String ph_number;
    private String dob;
    private String address;
    private String referral_code;
    private Long totalPoints;
    private List<ReferralDetails> referralDetails;

    public List<ReferralDetails> getReferralDetails() {
        return referralDetails;
    }

    public void setReferralDetails(List<ReferralDetails> referralDetails) {
        this.referralDetails = referralDetails;
    }

    public Long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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


}


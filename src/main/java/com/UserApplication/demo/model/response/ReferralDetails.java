package com.UserApplication.demo.model.response;

import java.util.List;

public class ReferralDetails {
    private String referral_email;
    private Long points;

    public String getReferral_email() {
        return referral_email;
    }

    public void setReferral_email(String referral_email) {
        this.referral_email = referral_email;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}

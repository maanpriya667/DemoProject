package com.UserApplication.demo.entity;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("userOtp")

public class OTPEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String otp;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

}


package com.paisley.dto;

import java.util.Map;

public class OTPDetails {
    private final String otp;
    private final long expiryTime;

    // Constructor to initialize OTP and expiry time
    public OTPDetails(String otp, long expiryTime) {
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    public OTPDetails(Map<String, String> otpDetailsMap) {
        this.otp = otpDetailsMap.get("otp");
        this.expiryTime = Long.parseLong(otpDetailsMap.get("expiryTime"));
    }

    // Getter for OTP
    public String getOtp() {
        return otp;
    }


    // Check if the OTP is expired based on the current time
    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }

    @Override
    public String toString() {
        return (otp + ":" + expiryTime);
    }
}
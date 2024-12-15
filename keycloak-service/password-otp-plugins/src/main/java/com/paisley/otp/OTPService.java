package com.paisley.otp;

import com.paisley.dto.OTPDetails;

import java.security.SecureRandom;

public class OTPService {

    // String containing digits to generate OTP
    private final String DIGITS;

    // SecureRandom for generating random numbers securely
    private static final SecureRandom random = new SecureRandom();

    // Length of the OTP
    private final int otpLength;

    // Expiry time for each OTP in milliseconds
    private final long expiryTime;

    // Private constructor to prevent instantiation
    private OTPService() {
        this.otpLength = Integer.parseInt(System.getenv("OTP_LENGTH"));  // Read from environment
        this.expiryTime = Long.parseLong(System.getenv("OTP_EXPIRY_TIME")); // Read from environment
        this.DIGITS = System.getenv("OTP_DIGITS");  // Digits string (e.g., "0123456789")
    }

    // Method to generate a new OTP and return OTPDetails DTO
    public OTPDetails generateOTP() {
        String otp = generateRandomOTP();
        long currentTime = System.currentTimeMillis();
        long otpExpiryTime = currentTime + expiryTime;  // OTP expiry time

        return new OTPDetails(otp, otpExpiryTime);  // Return the OTP and expiry time wrapped in DTO
    }

    // Method to generate a random OTP of specified length
    private String generateRandomOTP() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            otp.append(DIGITS.charAt(random.nextInt(DIGITS.length())));  // Append random character from DIGITS
        }
        return otp.toString();
    }

    // Static inner class responsible for holding the Singleton instance
    private static class InstanceHolder {
        // Create the Singleton instance in a thread-safe manner
        private static final OTPService INSTANCE = new OTPService();
    }

    // Public method to provide access to the Singleton instance
    public static OTPService getInstance() {
        return InstanceHolder.INSTANCE;
    }
}

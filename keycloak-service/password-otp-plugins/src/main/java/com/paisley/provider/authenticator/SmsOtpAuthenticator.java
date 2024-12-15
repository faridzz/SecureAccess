package com.paisley.provider.authenticator;

import com.paisley.dto.OTPDetails;
import com.paisley.otp.OTPService;
import jakarta.ws.rs.core.Response;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SmsOtpAuthenticator implements Authenticator {

    private static final Logger log = LoggerFactory.getLogger(SmsOtpAuthenticator.class);
    private final OTPService otpService = OTPService.getInstance();

    public SmsOtpAuthenticator() {
        // Start OTP generation
    }

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        String phoneNumber = context.getUser().getUsername();

        if (phoneNumber == null) {
            Response challenge = context.form()
                    .setError("Phone number not found")
                    .createErrorPage(Response.Status.BAD_REQUEST);
            context.failureChallenge(AuthenticationFlowError.INVALID_USER, challenge);
            return;
        }

        try {
            OTPDetails otp = otpService.generateOTP();
            log.debug("##### Generated OTP: {}", otp);
            context.getAuthenticationSession().setAuthNote("SMS_OTP", otp.toString());
            Response challenge = context.form().createForm("otp-login.ftl");
            context.challenge(challenge);

        } catch (Exception e) {
            log.error("##### Exception during OTP authentication process", e);
            Response challenge = context.form()
                    .setError("Internal server error during OTP generation")
                    .createErrorPage(Response.Status.INTERNAL_SERVER_ERROR);
            context.failureChallenge(AuthenticationFlowError.INTERNAL_ERROR, challenge);
        }
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        String expectedOtp;
        String enteredOtp = context.getHttpRequest().getDecodedFormParameters().getFirst("otp");
        String smsOtpSession = context.getAuthenticationSession().getAuthNote("SMS_OTP");
        Map<String, String> otpAndExpiry = extractOtpAndExp(smsOtpSession);
        OTPDetails otpDetails = new OTPDetails(otpAndExpiry);
        if (otpDetails.isExpired()) {
            otpDetails = otpService.generateOTP();
            expectedOtp = otpDetails.getOtp();
            context.getAuthenticationSession().removeAuthNote("SMS_OTP");
            context.getAuthenticationSession().setAuthNote("SMS_OTP", otpDetails.toString());
            log.debug("#####OTP has been expired  Entered OTP: {}, New Expected OTP: {}", enteredOtp, expectedOtp);
            Response challenge = context.form()
                    .setError(" OTP expired")
                    .createForm("otp-login.ftl");
            context.failureChallenge(AuthenticationFlowError.INVALID_CREDENTIALS, challenge);
        }
        if (!otpDetails.isExpired() && otpDetails.getOtp().equals(enteredOtp)) {
            context.getAuthenticationSession().removeAuthNote("SMS_OTP");
            context.success();
        } else {
            log.warn("##### OTP verification failed for user: {}", context.getUser().getUsername());
            Response challenge = context.form()
                    .setError("Incorrect OTP")
                    .createForm("otp-login.ftl");
            context.failureChallenge(AuthenticationFlowError.INVALID_CREDENTIALS, challenge);
        }


    }

    @Override
    public void close() {
    }

    @Override
    public boolean requiresUser() {
        return true;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
    }

    private Map<String, String> extractOtpAndExp(String otpDetails) {
        Map<String, String> otpMap = new HashMap<>();

        // Split the input string by ":" to separate OTP and expiry time
        String[] parts = otpDetails.split(":");

        // Check if the input is valid (i.e., it contains exactly two parts)
        if (parts.length == 2) {
            String otp = parts[0];          // OTP
            String expiryTime = parts[1];   // Expiry Time

            otpMap.put("otp", otp);          // Store OTP in map
            otpMap.put("expiryTime", expiryTime); // Store expiry time in map
        } else {
            throw new IllegalArgumentException("Invalid input format. Expected 'otp:expiryTime'");
        }

        return otpMap;
    }


}

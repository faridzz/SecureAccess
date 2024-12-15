
package com.paisley.provider.authenticator;

import com.paisley.otp.OTPGenerator;
import jakarta.ws.rs.core.Response;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class SmsOtpAuthenticatorHtmlLess extends SmsOtpAuthenticator {
    private static final Logger log = LoggerFactory.getLogger(SmsOtpAuthenticatorHtmlLess.class);

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        // Generate Access Code
        String code = context.generateAccessCode();
        // Store the code with a custom key in the user session notes
        String customKey = "user-auth-code";
        context.getAuthenticationSession().setUserSessionNote(customKey, code);
        // Get the Action URL based on the generated code (for sending login data to Keycloak)
        URI actionUrl = context.getActionUrl(code);
        String actionUrlStr = actionUrl.toString().replace(" ", "").trim();
        // Create JSON response with both URLs
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("Otp_check_url", actionUrlStr);
        // Get UserPhone number
        String phoneNumber = context.getUser().getUsername();

        if (phoneNumber == null) {

            // Send JSON response
            Response response = Response.status(Response.Status.BAD_REQUEST)
                    .entity("Phone number not found")
                    .header("Content-Type", "application/json")
                    .build();
            context.challenge(response);
            return;
        }

        try {
            String otp = super.otpGenerator.generateOTP(phoneNumber);
            log.debug("##### Generated OTP ...: {}", otp);
            context.getAuthenticationSession().setAuthNote("SMS_OTP", otp);
            // Send JSON response
            Response response = Response.status(Response.Status.OK)
                    .entity(urlMap)
                    .header("Content-Type", "application/json")
                    .build();
            context.challenge(response);

        } catch (Exception e) {
            log.error("##### Exception during OTP authentication process", e);
            Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("A error occurred during OTP creation process")
                    .header("Content-Type", "application/json")
                    .build();
            context.challenge(response);
        }
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        String enteredOtp = context.getHttpRequest().getDecodedFormParameters().getFirst("otp");
        String expectedOtp = context.getAuthenticationSession().getAuthNote("SMS_OTP");

        log.debug("##### Entered OTP: {}, Expected OTP: {}", enteredOtp, expectedOtp);

        if (OTPGenerator.validateOTP(context.getUser().getUsername(), enteredOtp)) {
            context.success();
        } else {
            log.warn("##### OTP verification failed for user: {}", context.getUser().getUsername());
            Response response = Response.status(Response.Status.BAD_REQUEST)
                    .entity("Otp is wrong or time outed")
                    .header("Content-Type", "application/json")
                    .build();
            context.challenge(response);
        }
    }
}

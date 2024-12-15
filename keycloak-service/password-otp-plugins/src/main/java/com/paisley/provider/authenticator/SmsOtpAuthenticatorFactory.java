package com.paisley.provider.authenticator;

import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ConfiguredProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsOtpAuthenticatorFactory implements AuthenticatorFactory, ConfiguredProvider {

    private static final Logger log = LoggerFactory.getLogger(SmsOtpAuthenticatorFactory.class);
    public static final String PROVIDER_ID = "sms-otp-authenticator";

    @Override
    public Authenticator create(KeycloakSession session) {
        log.debug("Creating instance of SmsOtpAuthenticator");//
        return new SmsOtpAuthenticator();
    }

    @Override
    public void init(org.keycloak.Config.Scope config) {
        log.debug("Initializing SmsOtpAuthenticatorFactory");//
    }

    @Override
    public void postInit(org.keycloak.models.KeycloakSessionFactory factory) {
        log.debug("Post-initialization of SmsOtpAuthenticatorFactory");//
    }

    @Override
    public void close() {
        log.debug("Closing SmsOtpAuthenticatorFactory");
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "SMS OTP Authenticator";
    }

    @Override
    public String getReferenceCategory() {
        return "sms-otp";
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return new AuthenticationExecutionModel.Requirement[]{
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.ALTERNATIVE,
                AuthenticationExecutionModel.Requirement.DISABLED
        };
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return "Authenticator that uses SMS OTP for multi-factor authentication.";
    }



    @Override
    public java.util.List<org.keycloak.provider.ProviderConfigProperty> getConfigProperties() {
        return java.util.Collections.emptyList();
    }
}

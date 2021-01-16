package com.thetechmaddy.authservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderFactory {

    private final AuthenticationProvider webAuthenticationProvider;

    @Autowired
    public AuthenticationProviderFactory(AuthenticationProvider webAuthenticationProvider) {
        this.webAuthenticationProvider = webAuthenticationProvider;
    }

    // TODO: Support mobile authentication with OTP based
    public AuthenticationProvider getProvider() {
        return this.webAuthenticationProvider;
    }
}

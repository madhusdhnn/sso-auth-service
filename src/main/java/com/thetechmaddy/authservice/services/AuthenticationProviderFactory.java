package com.thetechmaddy.authservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderFactory {

    private final CrsAuthenticationProvider webAuthenticationProvider;

    @Autowired
    public AuthenticationProviderFactory(CrsAuthenticationProvider webAuthenticationProvider) {
        this.webAuthenticationProvider = webAuthenticationProvider;
    }

    // TODO: Support mobile authentication with OTP based
    public CrsAuthenticationProvider getProvider() {
        return this.webAuthenticationProvider;
    }
}

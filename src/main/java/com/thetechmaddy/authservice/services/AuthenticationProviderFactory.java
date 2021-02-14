package com.thetechmaddy.authservice.services;

import com.thetechmaddy.authservice.services.auth.SsoAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderFactory {

    private final SsoAuthenticationProvider webAuthenticationProvider;

    @Autowired
    public AuthenticationProviderFactory(SsoAuthenticationProvider webAuthenticationProvider) {
        this.webAuthenticationProvider = webAuthenticationProvider;
    }

    // TODO: Support mobile authentication with OTP based
    public SsoAuthenticationProvider getProvider() {
        return this.webAuthenticationProvider;
    }
}

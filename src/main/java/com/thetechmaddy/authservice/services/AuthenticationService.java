package com.thetechmaddy.authservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService {

    private final AuthenticationProviderFactory authenticationProviderFactory;

    @Autowired
    public AuthenticationService(AuthenticationProviderFactory authenticationProviderFactory) {
        this.authenticationProviderFactory = authenticationProviderFactory;
    }

    public String initiate() {
        return authenticationProviderFactory.getProvider().loginURL();
    }

    public void logout(HttpServletRequest request) {
    }

}

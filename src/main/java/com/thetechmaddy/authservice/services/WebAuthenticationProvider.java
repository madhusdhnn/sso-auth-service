package com.thetechmaddy.authservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("webAuthenticationProvider")
public class WebAuthenticationProvider implements AuthenticationProvider {

    private final String loginUrl;

    public WebAuthenticationProvider(@Value("${login.url}") String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    public String loginURL() {
        return this.loginUrl;
    }

}

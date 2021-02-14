package com.thetechmaddy.authservice.services.auth;

import javax.servlet.http.HttpServletRequest;

public interface SsoAuthenticationProvider {

    String loginURL();

    void logout(HttpServletRequest request);
}

package com.thetechmaddy.authservice.services.auth;

import javax.servlet.http.HttpServletRequest;

public interface CrsAuthenticationProvider {

    String loginURL();

    void logout(HttpServletRequest request);
}

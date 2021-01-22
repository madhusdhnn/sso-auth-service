package com.thetechmaddy.authservice.services;

import com.thetechmaddy.authservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Root service class to handle all authentication related operations
 */
@Service
public class AuthenticationService {

    private final AuthenticationProviderFactory authenticationProviderFactory;
    private final AuthSessionManager authSessionManager;

    @Autowired
    public AuthenticationService(AuthenticationProviderFactory authenticationProviderFactory,
                                 AuthSessionManager authSessionManager) {
        this.authenticationProviderFactory = authenticationProviderFactory;
        this.authSessionManager = authSessionManager;
    }

    public String initiate() {
        return authenticationProviderFactory.getProvider().loginURL();
    }

    public void authenticate(HttpServletRequest request) {
        User userInSession = authSessionManager.fetchUserInSession(request);
        authSessionManager.createUserSession(request, userInSession);
    }

    public void logout(HttpServletRequest request) {
        authenticationProviderFactory.getProvider().logout(request);
    }

}

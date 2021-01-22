package com.thetechmaddy.authservice.services.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdentitySessionManagerFactory {

    private final UserSessionManager webIdentitySessionManager;

    @Autowired
    public IdentitySessionManagerFactory(UserSessionManager webIdentitySessionManager) {
        this.webIdentitySessionManager = webIdentitySessionManager;
    }

    //TODO: suport for mobile user
    public UserSessionManager getUserSessionManager() {
        return this.webIdentitySessionManager;
    }
}

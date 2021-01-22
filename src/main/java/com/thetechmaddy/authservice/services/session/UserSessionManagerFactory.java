package com.thetechmaddy.authservice.services.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSessionManagerFactory {

    private final UserSessionManager webUserSessionManager;

    @Autowired
    public UserSessionManagerFactory(UserSessionManager webUserSessionManager) {
        this.webUserSessionManager = webUserSessionManager;
    }

    //TODO: suport for mobile user
    public UserSessionManager getUserSessionManager() {
        return this.webUserSessionManager;
    }

}

package com.thetechmaddy.authservice.services;

import com.thetechmaddy.authservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class AuthSessionManager {

    private final UserSessionManagerFactory userSessionManagerFactory;


    @Autowired
    public AuthSessionManager(UserSessionManagerFactory userSessionManagerFactory) {
        this.userSessionManagerFactory = userSessionManagerFactory;
    }

    public HttpSession createSession(HttpServletRequest request, User user) {
        return this.userSessionManagerFactory.getUserSessionManager().create(request, user);
    }

    public User fetchUserWithSession(String accessToken) {
        return null;
    }

}

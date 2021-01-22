package com.thetechmaddy.authservice.services.auth;

import com.thetechmaddy.authservice.exceptions.SessionNotFoundException;
import com.thetechmaddy.authservice.services.AuthSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component("webAuthenticationProvider")
public class WebAuthenticationProvider implements CrsAuthenticationProvider {

    private final String loginUrl;
    private final AuthSessionManager authSessionManager;

    public WebAuthenticationProvider(@Value("${login.url}") String loginUrl, AuthSessionManager authSessionManager) {
        this.loginUrl = loginUrl;
        this.authSessionManager = authSessionManager;
    }

    @Override
    public String loginURL() {
        return this.loginUrl;
    }

    @Override
    public void logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null) {
            throw new SessionNotFoundException("Session not avialble");
        }
        authSessionManager.logout(httpSession);
    }

}

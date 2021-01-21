package com.thetechmaddy.authservice.security.handlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private final String loginUrl;

    public LoginFailureHandler(@Value("${login.url}") String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = getMessage(exception);
        String loginURLWithErrorMessage = String.format("%s?error=%s", this.loginUrl, errorMessage);

        String redirectURL = request.getParameter("redirect");
        if (redirectURL != null) {
            loginURLWithErrorMessage = String.format("%s&redirect=%s", loginURLWithErrorMessage, redirectURL);
        }
        response.sendRedirect(loginURLWithErrorMessage);
    }

    private String getMessage(AuthenticationException exception) {
        String message = "Sorry! But that did not work, try again";
        if (exception instanceof InternalAuthenticationServiceException) {
            message = "There was a problem signing in. Contact support";
        } else if (exception instanceof UsernameNotFoundException) {
            message = "User not found";
        }
        return message;
    }
}

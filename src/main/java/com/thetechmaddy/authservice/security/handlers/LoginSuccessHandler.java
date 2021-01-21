package com.thetechmaddy.authservice.security.handlers;

import com.thetechmaddy.authservice.models.User;
import com.thetechmaddy.authservice.services.AuthSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final String authSuccessCallbackUrl;
    private final AuthSessionManager authSessionManager;

    @Autowired
    public LoginSuccessHandler(@Value("${auth.sucessCallBack}") String authSuccessCallback,
                               AuthSessionManager authSessionManager) {
        this.authSuccessCallbackUrl = authSuccessCallback;
        this.authSessionManager = authSessionManager;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectURL = request.getParameter("redirect");
        User user = (User) authentication.getPrincipal();
        if (redirectURL != null) {
            HttpSession session = authSessionManager.createSession(request, user);
            response.sendRedirect(String.format("%s?access_token=%s&redirect=%s", this.authSuccessCallbackUrl, session.getId(), redirectURL));
        } else {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Redirect URL not provided");
        }
    }

}

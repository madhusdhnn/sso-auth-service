package com.thetechmaddy.authservice.services;

import com.thetechmaddy.authservice.exceptions.NoUserFoundException;
import com.thetechmaddy.authservice.exceptions.SessionNotFoundException;
import com.thetechmaddy.authservice.models.User;
import com.thetechmaddy.authservice.services.session.IdentitySessionManagerFactory;
import com.thetechmaddy.authservice.services.session.UserSessionManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Root service class to manage session operations
 */
@Service
public class AuthSessionManager {

    private final UserSessionManagerFactory userSessionManagerFactory;
    private final IdentitySessionManagerFactory identitySessionManagerFactory;
    private final SessionRepository<? extends Session> sessionRepository;

    @Autowired
    public AuthSessionManager(UserSessionManagerFactory userSessionManagerFactory,
                              IdentitySessionManagerFactory identitySessionManagerFactory,
                              SessionRepository<? extends Session> sessionRepository) {
        this.userSessionManagerFactory = userSessionManagerFactory;
        this.identitySessionManagerFactory = identitySessionManagerFactory;
        this.sessionRepository = sessionRepository;
    }

    public HttpSession createSession(HttpServletRequest request, User user) {
        return this.userSessionManagerFactory.getUserSessionManager().create(request, user);
    }

    public HttpSession createUserSession(HttpServletRequest request, User user) {
        return this.identitySessionManagerFactory.getUserSessionManager().create(request, user);
    }

    public User fetchUserInSession(HttpServletRequest request) {
        String accessToken = request.getParameter("access_token");
        Session session = sessionRepository.findById(accessToken);
        if (session == null) {
            throw new SessionNotFoundException(String.format("No active session for the id - %s", accessToken));
        }

        User user = this.userSessionManagerFactory.getUserSessionManager().getUserInSession(session);
        if (user.getEmployee() == null) {
            throw new NoUserFoundException(String.format("Could not find user in session with access token - %s", accessToken));
        }
        return user;
    }

    public void logout(HttpSession httpSession) {
        this.userSessionManagerFactory.getUserSessionManager().invalidate(httpSession);
    }
}

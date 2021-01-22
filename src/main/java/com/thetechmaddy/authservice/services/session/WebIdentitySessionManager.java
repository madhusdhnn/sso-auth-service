package com.thetechmaddy.authservice.services.session;

import com.thetechmaddy.authservice.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service("webIdentitySessionManager")
public class WebIdentitySessionManager implements UserSessionManager {

    private final Integer maxInactiveIntervalInSec;

    public WebIdentitySessionManager(@Value("${auth.maxInactiveInterval}") Integer maxInactiveIntervalInSec) {
        this.maxInactiveIntervalInSec = maxInactiveIntervalInSec;
    }

    @Override
    public HttpSession create(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(this.maxInactiveIntervalInSec);
        session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, user.id());
        session.setAttribute(IDENTITY_ATTRIBUTE_NAME, user);
        return session;
    }

}

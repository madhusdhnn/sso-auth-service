package com.thetechmaddy.authservice.services.session;

import com.thetechmaddy.authservice.models.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Log4j2
@Service("webUserSessionManager")
public class WebUserSessionManager implements UserSessionManager {

    private final Integer maxInactiveIntervalInSec;

    @Autowired
    public WebUserSessionManager(@Value("${auth.maxInactiveInterval}") Integer maxInactiveInterval) {
        this.maxInactiveIntervalInSec = maxInactiveInterval;
    }

    @Override
    public HttpSession create(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_ID_ATTRIBUTE_NAME, user.getUsername());
        session.setAttribute(COMPANY_ID_ATTRIBUTE_NAME, user.getCompanyId());
        session.setMaxInactiveInterval(this.maxInactiveIntervalInSec);
        return session;
    }
}
